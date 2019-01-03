package com.shoppingstreet.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



public class StringUtils {
    private static String[] binaryArray =   
        {"0000","0001","0010","0011",  
        "0100","0101","0110","0111",  
        "1000","1001","1010","1011",  
        "1100","1101","1110","1111"};  
//    private static String[] chineseDigits = new String[] { "��", "Ҽ", "��", "��", "��", "��", "½", "��", "��", "��"};
    public static String[] chineseDigits = new String[] { "��", "һ", "��", "��", "��", "��", "��", "��", "��", "��"};
	
    private static final char[] charBytes = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
		'i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    
    private static final char[] numberBytes = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    
    /**
     * ����ָ��λ�����������.
     * @param number
     * @return
     */
    public static String randomNumbers(int number) {
    	int count = 0; //���ɵ�����ĳ���
    	int i; //���ɵ������
    	final int maxNum = numberBytes.length;
    	StringBuffer randomStr = new StringBuffer("");
        Random r = new Random();
        while(count < number){
        	 //�����������ȡ����ֵ����ֹ���ɸ�����
	         i = Math.abs(r.nextInt(maxNum)); //���ɵ������Ϊ36-1
	         if (i >= 0 && i < numberBytes.length) {
	        	 randomStr.append(numberBytes[i]);
	          count ++;
	         }
        }
        return randomStr.toString();
    }
    
    
    
    public static String randomStrByNumber(int number) {
    	int count = 0; //���ɵ�����ĳ���
    	int i; //���ɵ������
    	final int maxNum = charBytes.length;
    	StringBuffer randomStr = new StringBuffer("");
        Random r = new Random();
        while(count < number){
        	 //�����������ȡ����ֵ����ֹ���ɸ�����
	         i = Math.abs(r.nextInt(maxNum)); //���ɵ������Ϊ36-1
	         if (i >= 0 && i < charBytes.length) {
	        	 randomStr.append(charBytes[i]);
	          count ++;
	         }
        }
        return randomStr.toString();
    }
    
    
    public static String randomUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "").toUpperCase();
	}
	public static String digitsTochinese(int i){
		//����10����Ҫ��д
		return chineseDigits[i];
	}
	public static String toAllUpperCase(String uuid) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < uuid.length(); i++) {
			char c = uuid.charAt(i);
			if (Character.isLowerCase(c)) {
				buffer.append(Character.toUpperCase(c));
			} else {
				buffer.append(c);
			}
		}
		return buffer.toString();
	}
	

	
	// ʮ�������ַ���תbyte����
		public static byte[] hexStringToBytes(String hexString) {
			if (hexString == null || hexString.equals("")) {
				return null;
			}
			hexString = hexString.toUpperCase();
			int length = hexString.length() / 2;
			char[] hexChars = hexString.toCharArray();
			byte[] d = new byte[length];
			for (int i = 0; i < length; i++) {
				int pos = i * 2;
				d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
			}
			return d;
		}

		private static byte charToByte(char c) {
			return (byte) "0123456789ABCDEF".indexOf(c);
		}

		// ����ת�ַ������Կո���
		public static String bytesToHexString(byte[] src) {
			StringBuilder stringBuilder = new StringBuilder("");
			if (src == null || src.length <= 0) {
				return null;
			}
			for (int i = 0; i < src.length; i++) {
				int v = src[i] & 0xFF;
				String hv = Integer.toHexString(v);
				if (hv.length() < 2) {
					stringBuilder.append(0);
				}
				if (i == src.length - 1) {
					stringBuilder.append(hv);
				} else {
					stringBuilder.append(hv);
				}
			}
			return stringBuilder.toString();
		}
		
		
		public static String bytesToHexStringNoAppendBit(byte[] src) {
			StringBuilder stringBuilder = new StringBuilder("");
			if (src == null || src.length <= 0) {
				return null;
			}
			for (int i = 0; i < src.length; i++) {
				int v = src[i] & 0xFF;
				String hv = Integer.toHexString(v);
				/*if (hv.length() < 2) {
					stringBuilder.append(0);
				}*/
				if (i == src.length - 1) {
					stringBuilder.append(hv);
				} else {
					stringBuilder.append(hv);
				}
			}
			return stringBuilder.toString();
		}
		
		
		public static String bytesToString(byte[] src, String split) {
			StringBuilder stringBuilder = new StringBuilder("");
			if (src == null || src.length <= 0) {
				return null;
			}
			for (int i = 0; i < src.length; i++) {
				int v = src[i] & 0xFF;
				String hv = String.valueOf(v);
				if (i == src.length - 1) {
					stringBuilder.append(hv);
				} else {
					stringBuilder.append(hv + split);
				}
			}
			return stringBuilder.toString();
		}
		

		public static String generateHexString(int paramInt) {
			StringBuffer localStringBuffer = new StringBuffer();
			Random localRandom = new Random();
			int i = 16;
			for (int j = 0; j < paramInt; j++) {
				if (j % 2 == 0) {
					localStringBuffer.append("0123456789ABCDEF".charAt(localRandom
							.nextInt(i)));
				} else {
					localStringBuffer.append("0123456789ABCDEF".charAt(localRandom
							.nextInt(i)) + " ");
				}
			}
			return localStringBuffer.toString();
		}

		public static byte[] decodeTripleDES(byte[] data, byte[] key)
				throws NoSuchAlgorithmException, NoSuchPaddingException,
				InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
			byte[] keys;
			if (key.length == 16) {
				keys = new byte[24];
				System.arraycopy(key, 0, keys, 0, 16);
				System.arraycopy(key, 0, keys, 16, 8);
			} else {
				keys = key;
			}

			Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
			SecretKey secretKey = new SecretKeySpec(keys, "DESede");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return cipher.doFinal(data);
		}

		public static boolean equals(byte[] b1, byte[] b2) {
			if (b1.length != b2.length) {
				return false;
			}
			for (int i = 0; i < b1.length; i++) {
				if (b1[i] != b2[i]) {
					return false;
				}
			}
			return true;
		}
	/** 
     *  
     * @return ת��Ϊ�������ַ���
     */  
    public static String bytes2BinaryStr(byte[] bArray){  
        String outStr = "";  
        int pos = 0;  
        for(byte b:bArray){  
            //����λ  
            pos = (b&0xF0)>>4;  
            outStr+=binaryArray[pos];  
            //����λ  
            pos=b&0x0F;  
            outStr+=binaryArray[pos];  
        }  
        return outStr;  
    }
    /**��������ת����16����
  ����* @param buf
  ����* @return
  ����*/
    public static String binaryToHexString(byte[] bytes) {
   		StringBuffer sb = new StringBuffer();
   		for (int i = 0; i < bytes.length; i++) {
   			String hex = Integer.toHexString(bytes[i] & 0xFF);
    	 	if (hex.length() == 1) {
    	 		hex = '0' + hex;
    	 	}
    	 	sb.append(hex.toUpperCase());
   		}
   		return sb.toString();
    }
    /**��16����ת��Ϊ������
  ����* @param hexStr
  ����* @return
  ����*/
	public static byte[] hexStringToBinary(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length()/2];
		for (int i = 0;i< hexStr.length()/2; i++) {
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
	
	//ʮ������תΪ�ַ�
	public static String hexStringToString(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	/**
	 * ��ĳ�����ڼӼ��������� eg:2013-06-28��+1���� 2013-06-29 ��+3����2013-07-01
	 * @param date ����
	 * @param dump ����
	 * @return
	 */
	public static String getDateByAddSomeDay(Date date,int dump){
		Calendar ca=Calendar.getInstance();
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");  //���������ʽ
        String today = sm.format(date);
        String[] timeArray= today.split("-");
        ca.set(Calendar.YEAR,Integer.parseInt(timeArray[0]));
        ca.set(Calendar.MONTH, Integer.parseInt(timeArray[1])-1);
        ca.set(Calendar.DAY_OF_MONTH,Integer.parseInt(timeArray[2]));
        ca.add(Calendar.DAY_OF_MONTH, dump);
        String someDay = sm.format(ca.getTime());
        return someDay;
	}
	
	/**
	 * ���ɹ�Կ
	 * @param pubkey
	 * @return
	 * add by yaoyuan
	 */
	public static String generatePublicKey(String pubkey) {
//		BASE64Encoder base64en = new BASE64Encoder();
//		String encode = base64en.encode(hexStringToBinary(pubkey));
//		encode = "-----BEGIN RSA PUBLIC KEY-----" + encode + "-----END RSA PUBLIC KEY-----";
//		if (encode.getBytes().length < 256) {
//			encode = org.apache.commons.lang.StringUtils.rightPad(encode, 256, "0");
//		}
//		return encode;
		return null;
	}
	
	/**
	 * ��ȡ��ǰʱ�� ��ȷ������
	 */
	public static String getCurrentTime(){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		 String currentTime = sdf.format(new Date());
		 return currentTime;
	}
	/** 
     * @����: BCD��תΪ10���ƴ�(����������) 
     * @����: BCD�� 
     * @���: 10���ƴ� 
     */  
    public static String bcd2Str(byte[] bytes) {  
        StringBuffer temp = new StringBuffer(bytes.length * 2);  
        for (int i = 0; i < bytes.length; i++) {  
            temp.append((byte) ((bytes[i] & 0xf0) >>> 4));  
            temp.append((byte) (bytes[i] & 0x0f));  
        }  
        return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp  
                .toString().substring(1) : temp.toString();  
    }  
  
    /** 
     * @����: 10���ƴ�תΪBCD�� 
     * @����: 10���ƴ� 
     * @���: BCD�� 
     */  
    public static byte[] str2Bcd(String asc) {  
        int len = asc.length();  
        int mod = len % 2;  
        if (mod != 0) {  
            asc = "0" + asc;  
            len = asc.length();  
        }  
        byte abt[] = new byte[len];  
        if (len >= 2) {  
            len = len / 2;  
        }  
        byte bbt[] = new byte[len];  
        abt = asc.getBytes();  
        int j, k;  
        for (int p = 0; p < asc.length() / 2; p++) {  
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {  
                j = abt[2 * p] - '0';  
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {  
                j = abt[2 * p] - 'a' + 0x0a;  
            } else {  
                j = abt[2 * p] - 'A' + 0x0a;  
            }  
            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {  
                k = abt[2 * p + 1] - '0';  
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {  
                k = abt[2 * p + 1] - 'a' + 0x0a;  
            } else {  
                k = abt[2 * p + 1] - 'A' + 0x0a;  
            }  
            int a = (j << 4) + k;  
            byte b = (byte) a;  
            bbt[p] = b;  
        }  
        return bbt;  
    }
    /**
     * ȥ���ַ����еķ���${}��ʽ���Ӵ�.
     * @param
     * @return ȥ��${}���ַ���
     */
    public static String escape$String(String input) {
        return input.replaceAll("\\$\\{[^}]*\\}", "");
    }

    public static String replace$String(String input, String newStr) {
        return input.replaceAll("\\$\\{[^}]*\\}", newStr);
    }

    public static String replace$SpecifyString(String input, String str,
            String newStr) {
    	if(input != null){
    		input = input.replaceAll("\\$\\{" + str + "\\}", newStr);
    	}
        return input;
    }
    
    public static String replace$String(Map<String, Object> map, String src) {
    	if (src != null && map != null) {
    		for (String key : map.keySet()) {
				String value = String.valueOf(map.get(key));
				src = replace$SpecifyString(src, key, value);
			}
    	}
    	return src;
    	
    }
	/**
	 * ������֤��
	 * @return
	 */
	public static String createValidateCode(){
		String str = "0,1,2,3,4,5,6,7,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,8,9,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D";
		String str2[] = str.split(",");//���ַ�����,�ָ�
		String code="";
		for(int i=0;i<4;i++){
			int a=(int)(Math.random()*36);
			code=code+str2[a];
		}
		return code;
	}
	/**
	 * �������֤��������
	 * @param idNumber
	 * @return
	 */
	public static int calculateAgeByIdNumber(String idNumber){
		int leh = idNumber.length();
		int years =0;
		if (leh == 18) {
			years=Integer.parseInt(idNumber.substring(6, 10));
		}else {
			years = Integer.parseInt("19" + idNumber.substring(6, 8));
		}
		Calendar a=Calendar.getInstance();
		return a.get(Calendar.YEAR)-years;
	}
	/**
	 * �������֤�����Ա�
	 * @param idNumber
	 * @return
	 */
	public static int calculateSexByIdNumber(String idNumber){
		int leh = idNumber.length();
		int se =0;
		if (leh == 18) {
			se=Integer.valueOf(idNumber.substring(16,17)) % 2;
		}
		return (se==1?1:2);
	}
	
	/**
	 * ���������������е�����.
	 * @param sortedData ��������
	 * @param findValue
	 * @return
	 */
	public static int searchValueSectionInArr(Integer[] sortedData,int findValue) {
		int start = 0;
		int end = 0;
		for (int i = 0; i < sortedData.length; i++) {
			if (findValue >= sortedData[i]) {
				start = i;
			} else {
				end = i;
				break;
			}
		}
		return start;
	}
	
	/** 
     * ѭ�����ֲ����������䣬���ص�һ�γ��ָ�ֵ��λ�� 
     * @param sortedData    ����������� 
     * @param findValue     ��Ҫ�ҵ�ֵ 
     * @return ֵ�������е�λ�ã���0��ʼ���Ҳ�������-1 
     */  
    public static int binarySearch(Integer[] sortedData,int findValue)  {  
        int start=0;  
        int end=sortedData.length-1;  
          
        while(start<=end)  {  
            //�м�λ��  
            int middle=(start+end)>>1;    //�൱��(start+end)/2
     //   System.out.println("middle==>>" + middle);
            //��ֵ  
            int middleValue=sortedData[middle];  

             if(findValue < middleValue)  {
                //С����ֵʱ����ֵǰ����  
                end = middle-1;
                //���С��ǰ�ߵ�ֵ ��ǰ ǰ�ߵ�ֵ  �������һ�β���
                if (end >= 0) {
                	int end_v =sortedData[end];
                    if (findValue >= end_v) {
                    	return end;
                    }
                } else {
                	return middle;
                }
            }  
            else   {  
                //������ֵ����ֵ������  
                start=middle+1;
                if (start <= sortedData.length -1) {
                	int end_v = sortedData[start];
                    if (findValue < end_v) {
                    	return middle;
                    }
                } else {
                	return middle;
                }
            }  
        }  
        //�Ҳ���  
        return -1;  
    }  
	
	

    public static void main(String[] args) {
		Integer age=calculateAgeByIdNumber("133022198201242396");
		Integer sex=calculateSexByIdNumber("133022198201242396");
		System.out.println("age"+age+">>>>>>sex>>>>>>>>>"+sex);
	}
}
