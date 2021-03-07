package mayton;

import java.awt.Color;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Различные тулзы на все случаи
 * @author mayton
 */
public class Utility {

    public static class Num
    {
        public final static int DG_POWER=6;

          private static final String[][] a_power = new String[][]{
           {"0", ""            , ""             ,""              },  // 1
           {"1", "тысяча "     , "тысячи "      ,"тысяч "        },  // 2
           {"0", "миллион "    , "миллиона "    ,"миллионов "    },  // 3
           {"0", "миллиард "   , "миллиарда "   ,"миллиардов "   },  // 4
           {"0", "триллион "   , "триллиона "   ,"триллионов "   },  // 5
           {"0", "квадриллион ", "квадриллиона ","квадриллионов "},  // 6
           {"0", "квинтиллион ", "квинтиллиона ","квинтиллионов "}   // 7
          };

          private static final String[][] digit = new String[][] {
           {""       ,""       , "десять "      , ""            ,""          },
           {"один "  ,"одна "  , "одиннадцать " , "десять "     ,"сто "      },
           {"два "   ,"две "   , "двенадцать "  , "двадцать "   ,"двести "   },
           {"три "   ,"три "   , "тринадцать "  , "тридцать "   ,"триста "   },
           {"четыре ","четыре ", "четырнадцать ", "сорок "      ,"четыреста "},
           {"пять "  ,"пять "  , "пятнадцать "  , "пятьдесят "  ,"пятьсот "  },
           {"шесть " ,"шесть " , "шестнадцать " , "шестьдесят " ,"шестьсот " },
           {"семь "  ,"семь "  , "семнадцать "  , "семьдесят "  ,"семьсот "  },
           {"восемь ","восемь ", "восемнадцать ", "восемьдесят ","восемьсот "},
           {"девять ","девять ", "девятнадцать ", "девяносто "  ,"девятьсот "}
          };

          public static String toString(int sum){
            int    i,mny;
            StringBuffer result= new StringBuffer("");
            long divisor; //делитель
            int psum = sum;

            int one  = 1;
            int four = 2;
            int many = 3;

            int sex  = 0;
            int hun  = 4;
            int dec  = 3;
            int dec2 = 2;

            if(sum == 0) return "ноль ";
            if(sum <  0){ result.append("минус "); psum = -psum;}

            for(i=0,divisor=1; i<DG_POWER; i++) divisor *= 1000;

            for(i=DG_POWER-1; i>=0; i--){
              divisor /= 1000;
              mny = (int)(psum / divisor);
              psum %= divisor;
              //str="";
              if(mny==0){
                if(i>0) continue;
                result.append( a_power[i][one] );
              }else{
                if(mny>=100){ result.append( digit[mny/100][hun]); mny%=100; }
                if(mny>=20 ){ result.append( digit[mny/10 ][dec]); mny%=10; }
                if(mny>=10 ){
                        result.append( digit[mny-10 ][dec2]);
                }else{
                  if(mny>=1  ) result.append( digit[mny][ "0".equals(a_power[i][0]) ? 0 : 1 ] );
                }
                switch(mny){
                  case  1: result.append( a_power[i][one] ); break;
                  case  2:
                  case  3:
                  case  4: result.append( a_power[i][four]); break;
                  default: result.append( a_power[i][many]); break;
                };
              }
            }
            return result.toString();
          }

          public static String toString(double num){
                return toString( (int)num) + "."+ toString( (int)(num*100 - ((int)num)*100) );
          }

    }

    /**
     * Получение строкового представления MAC адреса
     * из массива байтов
     * @param b
     * @return
     */
    public static String getMAC(byte[] b)
    {
        StringBuffer sb=new StringBuffer("");
        if (b!=null){
            for(int i=0;i<b.length;i++) {
                sb.append(String.format("%02X",b[i]));
                if (i!=b.length-1) sb.append(":");
            }
        }
        return sb.toString();
    }

    /**
     * Конвертация InetAddress => Строковое представление
     * @param ia
     * @return
     */
    public static String getIP(InetAddress ia){
        if (ia!=null){
            return getIP(ia.getAddress());
        }
        else
        {
            return "";
        }
    }
    /**
     * Получение строкового представления IPv4 адреса 
     * из массива байтов
     * @param b
     * @return
     */
    public static String getIP(byte[] b)
    {
        StringBuffer sb=new StringBuffer("");
        if (b!=null){
            for(int i=0;i<b.length;i++) {
                sb.append(b[i]<0?b[i]+256:b[i]);                
                if (i<b.length-1) sb.append(".");
            }
        }
        return sb.toString();
    }
    /**
     * Получение Human-readable представления целого числа
     * килобайтов, мегабайтов, гигабайтов
     * @param number
     * @return
     */
    public static String normalizeNumber(long number)
    {
            String[] units={"","K","M","G"};
            int i=0;
            while(i<units.length)
            {
                if (number>=1024)
                {
                    number/=1024;
                    i++;
                }
                else
                {
                    break;
                }
            }
            return String.format("%d%s",number,units[i]);
    }
    /**
     * Получение цвета java.awt.Color из RRGGBB представления
     * @param sign
     * @return
     */
    public static Color getColorFromRGBSignature(String sign)
    {
        int r=0;
        int g=0;
        int b=0;
        r=Integer.valueOf(sign.substring(0,2),16);
        g=Integer.valueOf(sign.substring(2,4),16);
        b=Integer.valueOf(sign.substring(4,6),16);
        return new Color(r,g,b);
    }

    /**
     * Расширяет число до ближайше степени двойки в большую сторону
     * @param i
     * @return
     */
    public static int extendToPowerOf2(int i)
    {
	      	if (i<0x00000002) return 0x00000002;
	       	if (i<0x00000004) return 0x00000004;
	       	if (i<0x00000008) return 0x00000008;
	       	if (i<0x00000010) return 0x00000010;
	       	if (i<0x00000020) return 0x00000020;
	       	if (i<0x00000040) return 0x00000040;
	       	if (i<0x00000080) return 0x00000080;
	       	if (i<0x00000100) return 0x00000100;
		return 0x40000000;
    }

    /**
     * Проверяет, является ли строка числом в Римкой системе
     * @param s
     * @return
     */
    public static boolean isRomanString(String s)
    {
        Pattern p=Pattern.compile("^(?i)M{0,3}(D?C{0,3}|C[DM])(L?X{0,3}|X[LC])(V?I{0,3}|I[VX])$");        
        Matcher m=p.matcher(s);
        return m.matches();
    }

    /**
     * Экранирование Oracle символов кавычек
     * @param str
     * @return
     */
    public static String wrapOracleString(String str)
    {
        assert(str!=null);
        return str.replace("'", "''");
    }

    /**
     * Враппинг национальных символов для Ansi представления в Oracle
     * @param str
     * @return
     */
    public static String wrapOracleANSIString(String str)
    {
        assert(str!=null);
        StringBuffer Sb=new StringBuffer("");
        for(int i=0;i<str.length();i++)
        {
            short k=(short)str.charAt(i);
            if (k==0x27)
            {
                Sb.append("''");
                continue;
            }
            if ((k<32)||(k>=128))
            {
                Sb.append(String.format("\\%04X", k));
                continue;
            }
            Sb.append((char)k);
        }
        return Sb.toString();
    }

    /**
     * Возвращает расширение
     * @param path
     * @return
     */
    public static String getExtension(String path)
    {
          int lif=path.lastIndexOf('.');
          lif++;
          if (lif<=0) return "";
          return path.substring(lif).toLowerCase();
    }

    /**
     * Преобразует левые слеши в правые
     * @param s
     * @return
     */
    public static String getUnixFilePath(String s)
    {
        return s.replace("\\", "/");
    }

 

    /**
     * Возвращает имя файла
     * @param path
     * @return
     */
    public static String getFileOnly(String path)
    {
          int lif=path.lastIndexOf('.');
          lif++;
          if (lif<=0) return "";
          return path.substring(0,lif-1);
    }

    /**
     * Возвращает дату в формате XML-даты времени
     * @param date
     * @return
     */
    public static String getXMLDateTime(Date date)
    {
        SimpleDateFormat XMLDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat XMLTimeFormat=new SimpleDateFormat("HH:mm:ss");
        return XMLDateFormat.format(date)+"T"+XMLTimeFormat.format(date);
    }

    /**
     * Конвертация массива байт в binHex представление строки
     * @param hex
     * @return
     */
    public static String byteArrayToHexString(byte[] hex)
    {
        assert(hex!=null);
        StringBuffer Sb=new StringBuffer("");
        String HexByte;
        for(int i=0;i<hex.length;i++)
        {
            int k=(int)hex[i];
            if (k<0) k+=256;
            HexByte=Integer.toHexString(k).toUpperCase();
            if (HexByte.length()==1) Sb.append("0");
            Sb.append(HexByte);
        }
        return Sb.toString();
    }

    /**
     * Конвертация строки из BinHex представления в массив байт
     * @param str - длина должна быть чётной
     * @return
     */
    public static byte[] hexStringToByteArray(String str)
    {
        assert(str!=null);
        String hexc="0123456789ABCDEF";
        str=str.toUpperCase();
        byte[] arr=new byte[str.length()/2];
        int c=0;
        int j=0;
        int v=0;
        for(int i=0;i<str.length();i++)
        {
            if (c==0)
            {
                v=0;
                v|=(hexc.indexOf(str.charAt(i)))<<4;
                c++;
            }
            else
            {
                c=0;
                v|=hexc.indexOf(str.charAt(i));
                arr[j++]=(byte)(v-256);
            }
        }
        return arr;
    }
   
}

