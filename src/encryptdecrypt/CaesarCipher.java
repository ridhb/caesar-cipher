package encryptdecrypt;

import com.rmtheis.yandtran.detect.Detect;
import com.rmtheis.yandtran.language.Language;
import com.rmtheis.yandtran.translate.Translate;



public class CaesarCipher {
	
	public static String bacaString(){
        int karakter;
        String str = "";
        boolean selesai =false;
         
        while(!selesai){
            try{
                karakter =System.in.read();
                if (karakter <0 || (char) karakter == '\n')
                    selesai=true;
                else if ((char) karakter != '\r')
                    str = str + (char) karakter;
                }
            catch(java.io.IOException e) {
                System.err.println("ada kesalahan");
                selesai = true;
            }
        }
      return str;
    }
	    
	private static String encrypting(String st, int rtint){
        int pjgstr=st.length();
        char [] ciphertext = new char [pjgstr];
       
        int[] ascist;
        ascist = new int [pjgstr];
 
        int[] cipherasci1;
        cipherasci1 = new int [pjgstr];
        int[] cipherasci2;
        cipherasci2 = new int [pjgstr];
        int salah = 0;
 
        if (rtint >= 26)
            rtint = rtint%25;
      

        for (int a=0; a < pjgstr; a++) {
        ascist[a] = String.valueOf(st).codePointAt(a);

        if (ascist[a] >= 97 && ascist[a] <= 122 ){
            cipherasci1 [a] = ascist[a]+rtint;
            if (cipherasci1 [a] >= 123)
                cipherasci2 [a] = 97 + (cipherasci1 [a] % 123);
            else
                cipherasci2 [a] = cipherasci1 [a];   
        }
        else if (ascist[a] >= 65 && ascist[a] <= 90 ) {
            cipherasci1 [a] = ascist[a]+rtint;
            if (cipherasci1 [a] >= 91)
                cipherasci2 [a] = 65 + (cipherasci1 [a] % 91);
            else
                cipherasci2 [a] = cipherasci1 [a];
        }
        else if (ascist[a] == 32 ) {
            cipherasci2 [a] = 32;
        }
 
        else {
            salah = 1;
        }
        
        ciphertext [a] = (char) cipherasci2 [a];
            }
        
        String Ct = String.valueOf(ciphertext);
     
        if (salah == 1)
            System.out.println("==error== (masukan hanya huruf besar / kecil dan spasi)"); 
        return Ct;
	}
	
	private static String decrypting(String st, int rtint){
        int pjgstr=st.length();
        char [] ciphertext = new char [pjgstr];
      
        int[] ascist;
        ascist = new int [pjgstr];
 
        int[] cipherasci1;
        cipherasci1 = new int [pjgstr];
        int[] cipherasci2;
        cipherasci2 = new int [pjgstr];
        int salah = 0;
 
        if (rtint >= 26)
            rtint = rtint%25;
     
        
        for (int a=0; a < pjgstr; a++) {
        ascist[a] = String.valueOf(st).codePointAt(a);
  
        if (ascist[a] >= 97 && ascist[a] <= 122 ){
        	
            
            cipherasci1 [a] = ascist[a]-rtint;
            if (cipherasci1 [a] <= 96)
                cipherasci2 [a] = -96 + 122 + (cipherasci1 [a] % 97);
            else
                cipherasci2 [a] = cipherasci1 [a]; 
          
        }
       
        else if (ascist[a] >= 65 && ascist[a] <= 90 ) {
            cipherasci1 [a] = ascist[a]-rtint;
            if (cipherasci1 [a] <= 64)
                cipherasci2 [a] = -64 + 90 + (cipherasci1 [a] % 65);
            else
                cipherasci2 [a] = cipherasci1 [a];
        }
        else if (ascist[a] == 32 ) {
            cipherasci2 [a] = 32;
        }
 
        else {
            salah = 1;
        }
        
        ciphertext [a] = (char) cipherasci2 [a];
            }
        
        if (salah == 1)
            System.out.println("==error== (masukan hanya huruf besar / kecil dan spasi)");

        String pt = String.valueOf(ciphertext);
        return pt;
		
	}
	
	private static Language detect (String st) throws Exception{
		Translate.setKey("trnsl.1.1.20160321T164738Z.fba739236bac1dcf.7a40c99b98271b06608e000efb3577ed0282a8bd");
		 Language detectText2 = Detect.execute(st);
		// Boolean status = detectText2!=null;
		 return detectText2;
	}
	
	private static Boolean detect2 (String st, Language lang) throws Exception{
		  Translate.setKey("trnsl.1.1.20160321T164738Z.fba739236bac1dcf.7a40c99b98271b06608e000efb3577ed0282a8bd");
		  
		  Language lang2 = Language.GERMAN;
		  Language lang1 = Language.INDONESIAN;
		  String translatedText;
		  if (lang != lang2) {
			  translatedText = Translate.execute(st, lang, lang2);
		  }
		  else {
			  translatedText = Translate.execute(st, lang, lang1);
		  }
		  Language detectText = Detect.execute(translatedText);
		  Boolean status = detectText==lang2;
		  return status;
	}
	
	private static String [] BruteForce (String st){
		String plainAr [] = new String [26];
		for (int i=0; i < 26; i++){
			String plain = decrypting (st, i);
			plainAr [i]=plain;
		}
		return plainAr;
	}
	
	
	public static void main(String[] args) throws Exception{
	        System.out.print("Plaintext: ");
	        String st = bacaString();
	        System.out.print("ROT: ");
	        String rt = bacaString();
	        int rtint = Integer.parseInt(rt);
	        String cipher = encrypting (st, rtint);
	        System.out.print("Ciphertext: ");
	        System.out.println( cipher);
	        String plain = decrypting (cipher, rtint);
	        System.out.print("Plaintext: ");
	        System.out.println( plain );
	        System.out.println();
	        String [] plainAr = BruteForce (cipher);
	        
	       for (int i =0; i <26; i++){
	    	   Language Lang =  detect (plainAr[i]);
	    	    if (Lang!=null) {
	    	    	Boolean status2 =  detect2 (plainAr[i],Lang);
	    	    	if (status2 == true) {
	    	    		 System.out.print("Brute Force attack ke " + i + " : ");
	 		        	 System.out.println( plainAr[i]);
	    	    	}
	    	    }
	       }
	        	 
	        
	    }
	 }
	
	
	

	