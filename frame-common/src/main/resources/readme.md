注释模板:
**
 *
 $params$
 $return$
 * @throws
 * @author: lvmoney /XXXXXX科技有限公司
 * @date: $date$ $time$
 */
 
 $params$的Default value：
 groovyScript("if(\"${_1}\".length() == 2) {return '';} else {def result=''; def params=\"${_1}\".replaceAll('[\\\\[|\\\\]|\\\\s]', '').split(',').toList();for(i = 0; i < params.size(); i++) {if(i==0){result+='* @Param ' + params[i] + ': '}else{result+='\\n' + ' * @Param ' + params[i] + ': '}}; return result;}", methodParameters());
 $return$的Default value：
 groovyScript("def returnType = \"${_1}\"; def result = '* @return: ' + returnType; return result;", methodReturnType());