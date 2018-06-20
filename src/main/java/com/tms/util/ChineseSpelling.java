package com.tms.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChineseSpelling {
	/**
	 * Logger for this class
	 */
//	private static final Logger logger = Logger.getLogger(ChineseSpelling.class);
	private static final Logger log = LoggerFactory.getLogger(ChineseSpelling.class);
	 /**   
     * 汉字转换位汉语拼音首字母，英文字符不变   
     * @param chinese 汉字   
     * @return 拼音   
     */      
    public static String converterToFirstSpell(String chinese){
         String pinyinName = "";
        char[] nameChar = chinese.toCharArray();       
         HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();       
         defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);       
         defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);       
        for (int i = 0; i < nameChar.length; i++) {       
            if (nameChar[i] > 128) {       
                try {       
                     pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);       
                 } catch (BadHanyuPinyinOutputFormatCombination e) {
                	 log.error("汉字转换位汉语拼音首字母失败", e);
                 }       
             }else{       
                 pinyinName += nameChar[i];       
             }       
         }       
        return pinyinName;       
     }       
        
    /**   
     * 汉字转换位汉语拼音，英文字符不变   
     * @param chinese 汉字   
     * @return 拼音   
     */      
    public static String converterToSpell(String chinese){
         String pinyinName = "";
        char[] nameChar = chinese.toCharArray();       
         HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();       
         defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);       
         defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);       
        for (int i = 0; i < nameChar.length; i++) {       
            if (nameChar[i] > 128) {       
                try {       
                     pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];       
                 } catch (BadHanyuPinyinOutputFormatCombination e) {
                	 log.error("汉字转换位汉语拼音，英文字符不变发生异常", e);
                 }       
             }else{       
                 pinyinName += nameChar[i];       
             }       
         }       
        return pinyinName;       
     }   

}
