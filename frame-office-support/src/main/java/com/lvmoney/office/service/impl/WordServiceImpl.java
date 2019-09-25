package com.lvmoney.office.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/27
 * Copyright xxxx科技有限公司
 */


import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.NumbericRenderData;
import com.deepoove.poi.template.ElementTemplate;
import com.lvmoney.common.constant.CommonConstant;
import com.lvmoney.common.exceptions.BusinessException;
import com.lvmoney.common.exceptions.CommonException;
import com.lvmoney.common.utils.FileUtil;
import com.lvmoney.common.utils.MapUtil;
import com.lvmoney.common.utils.SnowflakeIdFactoryUtil;
import com.lvmoney.office.constant.OfficeConstant;
import com.lvmoney.office.enums.WTemplateEnum;
import com.lvmoney.office.service.WordService;
import com.lvmoney.office.vo.*;
import org.apache.commons.io.IOUtils;
import org.jodconverter.DocumentConverter;
import org.jodconverter.office.OfficeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Service
public class WordServiceImpl implements WordService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WordServiceImpl.class);

    @Autowired
    private DocumentConverter documentConverter;

    @Override
    public boolean officeChange(WordSourceVo wSourceVo) {
        File sourceFile = new File(wSourceVo.getSource());
        File targetFile = new File(wSourceVo.getTarget());
        if (!targetFile.exists()) {
            try {
                documentConverter.convert(sourceFile).to(targetFile).execute();
                return true;
            } catch (OfficeException e) {
                LOGGER.error("office文件转换报错:{}", e.getMessage());
                throw new BusinessException(CommonException.Proxy.WORD_2_OFFICE_ERROR);
            }
        }
        return false;
    }

    @Override
    public BaseChangeByteOutVo officeChange(BaseChangeFileVo baseChangeFileVo) {
        byte[] source = baseChangeFileVo.getResouce();
        SnowflakeIdFactoryUtil idWorker = new SnowflakeIdFactoryUtil(1, 2);
        String resName = String.valueOf(idWorker.nextId());
        String sourceFile = OfficeConstant.TEMP_FILE_PATH + OfficeConstant.FILE_SEPARATOR + resName + OfficeConstant.TEMP_SUFFIX;
        String targetName = String.valueOf(idWorker.nextId());
        String fileName = baseChangeFileVo.getFileName();
        String targetFile = OfficeConstant.TEMP_FILE_PATH + OfficeConstant.FILE_SEPARATOR + targetName + baseChangeFileVo.getFileName();
        FileUtil.byte2File(source, sourceFile);
        WordSourceVo wSourceVo = new WordSourceVo(sourceFile, targetFile);
        officeChange(wSourceVo);
        InputStream input = null;
        BaseChangeByteOutVo result = new BaseChangeByteOutVo();
        try {
            input = new FileInputStream(targetFile);
            result.setTarget(IOUtils.toByteArray(input));
            result.setFileName(fileName);
        } catch (FileNotFoundException e) {
            LOGGER.error("office文件转换报错:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.WORD_2_OFFICE_ERROR);
        } catch (IOException e) {
            throw new BusinessException(CommonException.Proxy.WORD_2_OFFICE_ERROR);
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                LOGGER.error("office文件转换报错:{}", e.getMessage());
            }
            File resFile = new File(sourceFile);
            File tarFile = new File(targetFile);
            resFile.delete();
            tarFile.delete();
        }
        return result;
    }

    @Override
    public boolean template2Word(WordTemplateVo templateVo) {
        String source = templateVo.getSource();
        String target = templateVo.getTarget();
        List<WordStringVo> str = templateVo.getStr();
        List<WordNumbericVo> numberic = templateVo.getNumberic();
        List<WordPictureVo> picture = templateVo.getPicture();
        List<WordTablesVo> table = templateVo.getTable();
        Map<String, Object> datas = new HashMap<String, Object>(CommonConstant.MAP_DEFAULT_SIZE) {
            {
                if (str != null) {
                    str.forEach(v -> {
                        put(v.getKey(), v.getValue());
                    });
                }
                if (numberic != null) {
                    numberic.forEach(v -> {
                        put(v.getKey(), new NumbericRenderData(v.getBody()));
                    });
                }
                if (table != null) {
                    table.forEach(v -> {
                        put(v.getKey(), new MiniTableRenderData(v.getTableHeads(), v.getTablebodys(), v.getDataDesc(), v.getWidth()));
                    });
                }
                if (picture != null) {
                    picture.forEach(v -> {
                        put(v.getKey(), v.getValue());
                    });
                }

            }
        };
        XWPFTemplate template = XWPFTemplate.compile(source)
                .render(datas);
        OutputStream out;
        try {
            out = new FileOutputStream(target);
            template.write(out);
            out.flush();
            out.close();
            template.close();
            return true;
        } catch (FileNotFoundException e) {
            LOGGER.error("word模板文件不存在:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.TEMPLATE_NOT_EXSIT);
        } catch (IOException e) {
            LOGGER.error("word模板文件转换成word文件失败:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.TEMPLATE_2_WORD_ERROR);
        }
    }

    @Override
    public BaseWordByteVo template2Word(WordTemplateByteVo templateVo) {
        byte[] source = templateVo.getSource();
        List<WordStringVo> str = templateVo.getStr();
        List<WordNumbericVo> numberic = templateVo.getNumberic();
        List<WordPictureVo> picture = templateVo.getPicture();
        List<WordTablesVo> table = templateVo.getTable();
        Map<String, Object> datas = new HashMap<String, Object>(MapUtil.initMapSize(16)) {
            {
                if (str != null) {
                    str.forEach(v -> {
                        put(v.getKey(), v.getValue());
                    });
                }
                if (numberic != null) {
                    numberic.forEach(v -> {
                        put(v.getKey(), new NumbericRenderData(v.getBody()));
                    });
                }
                if (table != null) {
                    table.forEach(v -> {
                        put(v.getKey(), new MiniTableRenderData(v.getTableHeads(), v.getTablebodys(), v.getDataDesc(), v.getWidth()));
                    });
                }
                if (picture != null) {
                    picture.forEach(v -> {
                        put(v.getKey(), v.getValue());
                    });
                }

            }
        };
        XWPFTemplate template = XWPFTemplate.compile(FileUtil.byte2Input(source))
                .render(datas);
        OutputStream out;
        SnowflakeIdFactoryUtil idWorker = new SnowflakeIdFactoryUtil(1, 2);
        String tempName = String.valueOf(idWorker.nextId());
        String temp = OfficeConstant.TEMP_FILE_PATH + "/" + tempName + OfficeConstant.TEMP_SUFFIX;
        BaseWordByteVo result = new BaseWordByteVo();
        try {
            out = new FileOutputStream(temp);
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (FileNotFoundException e) {
            LOGGER.error("word模板文件不存在:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.TEMPLATE_NOT_EXSIT);
        } catch (IOException e) {
            LOGGER.error("word模板文件转换成word文件失败:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.TEMPLATE_2_WORD_ERROR);
        } finally {
            File file = new File(temp);
            InputStream input = null;
            try {
                input = new FileInputStream(file);
                result.setFile(IOUtils.toByteArray(input));
                result.setFileName(file.getName());
            } catch (IOException e) {
                LOGGER.error("模板文件从零时目录中操作报错{}", e.getMessage());
                throw new BusinessException(CommonException.Proxy.TEMPLATE_2_WORD_ERROR);
            } finally {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.error("模板文件从零时目录中操作报错{}", e.getMessage());
                }

            }
            file.delete();
        }
        return result;
    }

    @Override
    public List<WordTemplateParams> getTemplateParams(WordSourceVo wSourceVo) {
        List<WordTemplateParams> result = new ArrayList<>();
        XWPFTemplate template = XWPFTemplate.compile(wSourceVo.getSource());
        List<ElementTemplate> elementTemplates = template.getElementTemplates();
        elementTemplates.forEach(v -> {
            WTemplateEnum wTemplateEnum = WTemplateEnum.getByValue(String.valueOf(v.getSign()));
            if (wTemplateEnum == null) {
                throw new BusinessException(CommonException.Proxy.TEMPLATE_ELEMENT_NOT_SUPPORT);
            }
            WordTemplateParams wordTemplateParams = new WordTemplateParams();
            wordTemplateParams.setParamName(v.getTagName());
            wordTemplateParams.setTemplateParam(v.getSource());
            wordTemplateParams.setWTemplateEnum(wTemplateEnum);
            result.add(wordTemplateParams);
        });
        return result;
    }


}
