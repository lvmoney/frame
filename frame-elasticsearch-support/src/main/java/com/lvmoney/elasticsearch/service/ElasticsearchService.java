/**
 * 描述:
 * 包名:com.lvmoney.elasticsearch.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月3日  下午1:55:48
 * Copyright xxxx科技有限公司
 */

package com.lvmoney.elasticsearch.service;

import com.lvmoney.elasticsearch.vo.ElasticsearchDeleteVo;
import com.lvmoney.elasticsearch.vo.ElasticsearchNearbyVo;
import com.lvmoney.elasticsearch.vo.ElasticsearchQueryVo;
import com.lvmoney.elasticsearch.vo.ElasticsearchSaveVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月3日 下午1:55:48
 */

public interface ElasticsearchService {
    @SuppressWarnings("rawtypes")
    <T> void save(ElasticsearchSaveVo elasticsearchSaveVo);

    /**
     * @return 2018年11月7日下午4:43:16
     * @describe:使用queryStringQuery完成单字符串查询,单字符串模糊查询，默认排序。 将从所有字段中查找包含传来的word分词后字符串的数据集
     * @author: lvmoney /xxxx科技有限公司
     */
    @SuppressWarnings("rawtypes")
    Object queryStringQuery(ElasticsearchQueryVo elasticsearchQueryVo);

    /**
     * @return 2018年11月7日下午4:46:29
     * @describe:单字段对某字符串模糊查询
     * @author: lvmoney /xxxx科技有限公司
     */
    @SuppressWarnings("rawtypes")
    Object matchQuery(ElasticsearchQueryVo elasticsearchQueryVo);

    /**
     * @param content
     * @param pageable
     * @return 2018年11月7日下午4:48:18
     * @describe:单字段对某短语进行匹配查询，短语分词的顺序会影响结果 和match查询类似，
     * match_phrase查询首先解析查询字符串来产生一个词条列表。
     * 然后会搜索所有的词条，但只保留包含了所有搜索词条的文档，
     * 并且词条的位置要邻接。一个针对短语“中华共和国”的查询不会匹配“
     * 中华人民共和国”，因为没有含有邻接在一起的“中华”和“共和国”词条。
     * <p>
     * 这种完全匹配比较严格，类似于数据库里的“%落日熔金%”这种，
     * 使用场景比较狭窄。如果我们希望能不那么严格，譬如搜索“中华共和国”，
     * 希望带“我爱中华人民共和国”的也能出来，就是分词后，
     * 中间能间隔几个位置的也能查出来，可以使用slop参数,
     * 表示少匹配一个分词也OK
     * @author: lvmoney /xxxx科技有限公司
     */
    Object matchPhraseQuery(ElasticsearchQueryVo elasticsearchQueryVo);

    /**
     * @param userId
     * @param pageable
     * @return 2018年11月7日下午4:48:32
     * @describe:term匹配，即不分词匹配，你传来什么值就会拿你传的值去做完全匹配
     * @author: lvmoney /xxxx科技有限公司
     */
    Object termQuery(ElasticsearchQueryVo elasticsearchQueryVo);

    /**
     * @param userId
     * @param pageable
     * @return 2018年11月7日下午4:48:32
     * @describe:terms匹配，即不分词匹配，多个值
     * @author: lvmoney /xxxx科技有限公司
     */
    Object termsQuery(ElasticsearchQueryVo elasticsearchQueryVo);

    /**
     * @param title
     * @param pageable
     * @return 2018年11月7日下午4:49:14
     * @describe:多字段匹配
     * @author: lvmoney /xxxx科技有限公司
     */
    Object multiMatchQuery(ElasticsearchQueryVo elasticsearchQueryVo);

    /**
     * @param elasticsearchQueryVo
     * @return 2018年11月7日下午4:50:56
     * @describe:单字段包含所有输入
     * @author: lvmoney /xxxx科技有限公司
     */
    Object containQuery(ElasticsearchQueryVo elasticsearchQueryVo);

    /**
     * @param elasticsearchNearbyVo
     * @return 2018年11月9日上午9:22:02
     * @describe:附近的数据，查询指定范围内附近的数据并排序 field
     * 根据elasticsearh的特性要求查询经纬度字段的格式必须是30.657500
     * ,104.079278 unit 指定范围单位
     * DistanceUnit.KILOMETERS千米
     * geoDistanceQueryBuilderDistance
     * 指定的范围。例如：2 结合单位unit表示2千米内
     * geoDistanceSortBuilderOrder 排序的方式
     * sortField 排序字段，一般和经纬度字段不同
     * @author: lvmoney /xxxx科技有限公司
     */
    Object nearby(ElasticsearchNearbyVo elasticsearchNearbyVo);

    /**
     * @param elasticsearchDeleteVo
     * @return 2018年11月9日上午9:52:47
     * @describe:根据id删除
     * @author: lvmoney /xxxx科技有限公司
     */
    @SuppressWarnings("rawtypes")
    boolean deleteById(ElasticsearchDeleteVo elasticsearchDeleteVo);

    /**
     * @param elasticsearchDeleteVo
     * @return 2019年1月14日下午3:36:00
     * @describe:fieldsMap k=查询字段,v=查询的值,使用matchQuery。遍历map后使用boolQuery.must
     * @author: lvmoney /xxxx科技有限公司
     */
    @SuppressWarnings("rawtypes")
    boolean deleteByBoolMatchQuery(ElasticsearchDeleteVo elasticsearchDeleteVo);

    /**
     * @param elasticsearchDeleteVo
     * @return 2018年11月9日上午9:53:04
     * @describe:根据索引删除
     * @author: lvmoney /xxxx科技有限公司
     */
    @SuppressWarnings("rawtypes")
    boolean deleteByIndex(ElasticsearchDeleteVo elasticsearchDeleteVo);

}
