package detectLang;

import com.rmtheis.yandtran.detect.Detect;
import com.rmtheis.yandtran.language.Language;
import com.rmtheis.yandtran.translate.Translate;


public class DetectL {
	
	public static void main(String[] args) throws  Exception{
		
		  Translate.setKey("trnsl.1.1.20160321T164738Z.fba739236bac1dcf.7a40c99b98271b06608e000efb3577ed0282a8bd");
		  Language lang1 = Language.ENGLISH;
		  Language lang2 = Language.GERMAN;
		  String translatedText = Translate.execute("when you saw him call me", lang1, lang2);
		  System.out.println(translatedText);
		  Language detectText = Detect.execute(translatedText);
	      System.out.println(detectText);
	}

}
