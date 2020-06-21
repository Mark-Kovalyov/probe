package mayton.dbf;


/*
 * @(#)DBFWriter.java 1.0 04/08/04
 * Autor: Kovalyov M.
 *
 */


// 26.08.2004 - Заложен первый камень
// 27.08.2004 - Проблема с кодировкой. Сделал фиксированную Cp866
//              хотя DBF-ники встречаются и с Windows-1251
//  1.09.2003 - Облом! FileOutputStream НЕ ПОДДЕРЖИВАЕТ ПРОИЗВОЛЬНЫЙ ДОСТУП! Делаем
//              RandomAccessFile!
//
//  2.09.2004 - Добавлен конструктор DBFWriter(RandomAccessFile SRAF)
//  3.09.2004 - Что мочить вместо булевых полей? (Фраза Даниленко... Хехе!)
//              ('y' 'n' или 'T' 'F')

// 15.09.2004 - Не нравится мне эта проверка:
//                  className.compareTo("java.lang.Float")==0

// 29.09.2004 - Непонятно что делать c float double типами. Как преобразовать в форматированный
//              fix-point стандарнтыми средствами

// 30.03.2005 - Миграция на Java 1.5.x


import java.io.*;
import java.util.*;
//import mayton.io.InvertRandomAccessFile;

import org.apache.commons.io.output.*;


// ¦         Запись заголовка в файле с данными               ¦
// ¦----------------------------------------------------------¦
// ¦ Байты :              Описание                            ¦
// ¦==========================================================¦
// ¦ 00    :Типы файлов с данными:                            ¦
// ¦       : FoxBASE+/dBASE III +, без memo - 0х03            ¦
// ¦       : FoxBASE+/dBASE III +, с memo - 0х83              ¦
// ¦       : FoxPro/dBASE IV, без memo - 0х03                 ¦
// ¦       : FoxPro с memo - 0хF5                             ¦
// ¦       : dBASE IV с memo - 0x8B                           ¦
// ¦----------------------------------------------------------¦
// ¦ 01-03 :Последнее изменение (ГГММДД)                      ¦
// ¦----------------------------------------------------------¦
// ¦ 04-07 :Число записей в файле                             ¦
// ¦----------------------------------------------------------¦
// ¦ 08-09 :Положение первой записи с данными                 ¦
// ¦----------------------------------------------------------¦
// ¦ 10-11 :Длина одной записи с данными (включая признак     ¦
// ¦       :удаления)                                         ¦
// ¦----------------------------------------------------------¦
// ¦ 12-27 :Зарезервированы                                   ¦
// ¦----------------------------------------------------------¦
// ¦ 28    :1-есть структ.составной инд.файл (типа .CDX),0-нет¦
// ¦----------------------------------------------------------¦
// ¦ 29-31 :Зарезервированы                                   ¦
// ¦----------------------------------------------------------¦
// ¦ 32-n  :Подзаписи полей**                                 ¦
// ¦----------------------------------------------------------¦
// ¦  n+1  :Признак завершения записи заголовка (0х01)        ¦

// Все типы данных сохряняются как символы
// Дата - 8 символов. ФОрмат YYYYMMDD.
// Для number - может указыватся точность дробной части
// Logical - принимает два значения 'T' или 'F'или '?' . Возможны варианты 't','f','Y','N','y','n'
//


/**
 * Константы и перечисления
 */

/*
enum  DBF_TYPES_ENUM
{
        CHAR     (0x43),
        NUMBER   (0x4E),
        LOGIC    (0x4C),
        MEMO     (0x4D),
        DATE     (0x44),
        FIXPOINT (0x46),
        TEMPLATE (0x50);
	private final int value;
	DBF_TYPES_ENUM(int value){this.value=value;}
	public int value(){return value;}
};
*/

class DBF_TYPES
{
	public static final int CHAR     = 0x43 ;
	public static final int NUMBER   = 0x4E ;
	public static final int LOGIC    = 0x4C ;
	public static final int MEMO     = 0x4D ;   // зарезервировано
	public static final int DATE     = 0x44 ;
	public static final int FIXPOINT = 0x46 ;
	public static final int TEMPLATE = 0x50 ;  // зарезервировано
};


class DBF_RECORD
{
	public static final int NORMAL   =0x20;
	public static final int DELETED  =0x2A;
}

class DBF_HEADER
{
	public static final int END_OF	 	=0x0D;
	public static final int INDEX_ENABLED	=0x01;
	public static final int INDEX_DISABLED	=0x00;
}

class DBF_SIGNATURE
{
	public static final int FoxBase_dBASE_III     =0x03;
	public static final int FoxBase_dBASE_III_memo=0x83;
	public static final int FoxPro_dBASE_IV       =0x03;
	public static final int FoxPro                =0xF5;
	public static final int dBASE_IV              =0x8B;
}

class BOOLEAN
{

}


/**
 * Класс <code>DBFFieldDef</code>
 *
 * @author  Ковалев Марк. (администратор баз данных сектора ОПО и АПУС)
 * @version 1.0, 04/08/04
 */

class DBFFieldDef
{
	String        name;
	int typeField;
	int length;
	int acc;

	public int getLength()
	{
		return length;
	}

	public int getAcc()
	{
		return acc;
	}

	public int getTypeField()
	{
		return typeField;
	}

	public String getName()
	{
		return name;
	}


/**
 * Конструктор поля DBF-файла
 * @param name - имя поля. При инициализации отсекается до 10 символов.
 * @param typeField - тип поля. Может принимать следующие значения:
 * C - символьное;
 * N - числовое;
 * L - логическое;
 * D - дата;
 * F - с плавающей точкой;
 * P - шаблон (в данной версии не используется.Зарезервировано);
 * M - типа memo (в данной версии не используется.Зарезервировано);
 * @param length - длина поля в символах. Имеет смысл только для числовых и символьных полей.
 * @param acc - количество дробных знаков после запятой. Имеет смысл только для числовых полей.
 */

	public DBFFieldDef(String name,char typeField,int length,int acc)
	{
		if (name.length()>10) throw new IllegalArgumentException("Argument 'name' is too big!");
		this.name=name;
		switch(typeField)
		{
			case 'C':this.typeField=DBF_TYPES.CHAR;break;
			case 'N':this.typeField=DBF_TYPES.NUMBER;break;
			case 'L':this.typeField=DBF_TYPES.LOGIC;break;
			case 'M':this.typeField=DBF_TYPES.MEMO;break;
			case 'D':this.typeField=DBF_TYPES.DATE;break;
			case 'F':this.typeField=DBF_TYPES.FIXPOINT;break;
			case 'P':this.typeField=DBF_TYPES.TEMPLATE;break;
			default:
				throw new IllegalArgumentException("Argument typeField is invalid!");

		}
		this.length=length;
	}

}

/**
 * Класс <code>DBFWriter</code> - генерация табличных файлов FoxBASE+/dBASE III (без memo)
 *
 * @author  Ковалев Марк. (администратор баз данных сектора ОПО и АПУС)
 * @version 1.0, 04/08/04
 */

public class DBFWriter
{



/**
  * Таблица преобразования из Unicode в Cp866 (Россия)
  */
	protected static final short[] UnicodeToCp866 = new short[]
	{
		0xF0,0x20,0x20,0x20,0x20,0x20,0x86,0x20,   // 0401 Ё.......
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x80,   // 0409 .......А
		0x81,0x82,0x83,0x84,0x85,0x86,0x87,0x88,   // 0411 БВГДЕЖЗИ
		0x89,0x8A,0x8B,0x8C,0x8D,0x8E,0x8F,0x90,   // 0419 ЙКЛМНОПР
		0x91,0x92,0x93,0x94,0x95,0x96,0x97,0x98,   // 0421 СТУФХЦЧШ
		0x99,0x9A,0x9B,0x9C,0x9D,0x9E,0x9F,0xA0,   // 0429 ЩЪЫЬЭЮЯа
		0xA1,0xA2,0xA3,0xA4,0xA5,0xA6,0xA7,0xA8,   // 0431 бвгдежзи
		0xA9,0xAA,0xAB,0xAC,0xAD,0xAE,0xAF,0xE0,   // 0439 йклмнопр
		0xE1,0xE2,0xE3,0xE4,0xE5,0xE6,0xE7,0xE8,   // 0441 стуфхцчш
		0xE9,0xEA,0xEB,0xEC,0xED,0xEE,0xEF,0x20,   // 0449 щъыьэюя.
		0xF1,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   // 0451 ё.......
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   // 0459 ........
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   // 0461 ........
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   // 0469 ........
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   // 0471 ........
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20    // 0479 ........
	};


/**
  * Таблица преобразования из Unicode в windows-1251 (Россия-Украина)
  */

	protected static final short[] UnicodeToWindows1251 = new short[]
	{
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   //
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   //
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   //
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   //
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   //
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   //
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   //
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   //
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   //
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   //
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   //
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   //
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   //
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   //
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   //
		0x20,0x20,0x20,0x20,0x20,0x20,0x20,0x20,   //
	};


	//protected InvertRandomAccessFile RAF;
        //protected SwappedDataOutputStream SDOS;

	protected int recordsOffset; // смещение первой записи
	protected int recordLength;  // длина записи в байтах c учетом флага удаления
	protected int recordsCount;  // количество записей
	protected int columnsCount;  // количество полей

	protected boolean existIndex=false;
	protected boolean columnsClosed=false;
	protected ArrayList <DBFFieldDef> FieldDefs;



/**
 * Конструктор вывода в файл
 * @param s путь к файлу
 */

	public DBFWriter(String s) throws IOException
	{
                SDIS=new SwappedDataInputStream();
		//RAF=new InvertRandomAccessFile(s,"rw");
		recordsCount=0;
		recordLength=1;
		columnsClosed=false;
		FieldDefs=new ArrayList <DBFFieldDef>();
	}


/**
 * Конструктор вывода в файл
 * @param s путь к файлу
 */

	public DBFWriter(RandomAccessFile SRAF) throws IOException
	{
		RAF=new InvertRandomAccessFile(SRAF);
		recordsCount=0;
		recordLength=1;
		columnsClosed=false;
		FieldDefs=new ArrayList<DBFFieldDef>();
	}


/**
 * Фиксирует дату последнего изменения в заголовке. Сохраняет заголовок. Сохраняет введенные записи
 * Закрывает DBF документ.
 */

	public void close() throws IOException
	{
		RAF.writeByte(0x1A);
		RAF.seek(4);
		RAF.writeInt(recordsCount);
		RAF.close();
	}

	private void writeDate(int year,int month,int day) throws IOException
	{
		RAF.writeByte(year);
		RAF.writeByte(month);
		RAF.writeByte(day);
	}

	protected void writeHeader() throws IOException
	{
		RAF.writeByte(DBF_SIGNATURE.FoxBase_dBASE_III);	// 03
		writeDate(4,12,8);				// 04 12 08
		RAF.writeInt(0x00000000);			// 00 00 00 00
		RAF.writeShort(32*columnsCount+32+1);		// ?? ??
		RAF.writeShort(recordLength);			// ?? ??
		RAF.writeInt(0x00000000);			// 00 00 00 00
		RAF.writeInt(0x00000000);			// 00 00 00 00
		RAF.writeInt(0x00000000);			// 00 00 00 00
		RAF.writeInt(0x00000000);			// 00 00 00 00
		RAF.writeInt(0x00000000);			// 00 00 00 00


		Iterator Itr=FieldDefs.iterator();

		int internal_pos=1;

		for(int i=0;i<columnsCount;i++)
		{
			Object obj=Itr.next();
			writeDOSString(((DBFFieldDef)obj).getName(),10);     // ?? ?? ?? ?? ??   ?? ?? ?? ?? ??

			RAF.writeByte(0x00);				     // 00

			RAF.writeByte(((DBFFieldDef)obj).getTypeField());    // ?? - (43|4E..)

			RAF.writeInt(internal_pos);			     // ?? ?? ?? ??

			internal_pos+=((DBFFieldDef)obj).getLength();

			RAF.writeByte(((DBFFieldDef)obj).getLength());	     // ??

			RAF.writeByte(((DBFFieldDef)obj).getAcc());	     // ??

			RAF.writeInt(0x00000000);		             // 00 00 00 00
			RAF.writeInt(0x00000000);		             // 00 00 00 00
			RAF.writeInt(0x00000000);		             // 00 00 00 00
			RAF.writeShort(0x0000);		             	     // 00 00

		}

		RAF.writeByte(DBF_HEADER.END_OF);	// 0D - завершение заголовка


	}

/**
 * Выводит в файл строку в кодировке cp866 длиной не более length байтов.
 * @param s строка
 * @param length длина поля в байтах
 */
	protected void writeDOSString(String s,int length) throws IOException
	{
		int s_length=s.length();

		if (s_length>length)  throw new IllegalArgumentException("Argument s is too long!");

		for(int i=0;i<s_length;i++)
		{
			char c=s.charAt(i);
			int v=(int)c;
			if (v<128) RAF.writeByte(v);
			else
			{
				// Здесь должно быть преобразование Unicode -> cp866

				RAF.writeByte(UnicodeToCp866[v-0x401]);
			}
		}
		for(int i=s_length;i<length;i++) RAF.writeByte(0x20);

	}



	protected void writeReal(Object obj,int length,int acc) throws IllegalArgumentException,IOException
	{
		String className=obj.getClass().getName();

		if (className.compareTo("java.lang.Float")==0)
		{
			// Здесь должно быть форматирование вещественного числа с учетом acc
			// .....................................
			//
			return;
		}
		if (className.compareTo("java.lang.Double")==0)
		{
			// Здесь должно быть форматирование вещественного числа с учетом acc
			// .....................................
			//
			return;
		}
		throw new IllegalArgumentException("Argument obj is invalid!");
	}


	protected void writeNumber(Object obj,int length) throws IllegalArgumentException,IOException
	{
		String className=obj.getClass().getName();

		if (className.compareTo("java.lang.Byte")==0)
		{
			writeDOSString(((Number)obj).toString(),length);
			return;
		}

		if (className.compareTo("java.lang.Short")==0)
		{
			writeDOSString(((Number)obj).toString(),length);
			return;
		}

		if (className.compareTo("java.lang.Integer")==0)
		{
			writeDOSString(((Number)obj).toString(),length);
			return;
		}

		if (className.compareTo("java.lang.Long")==0)
		{
			writeDOSString(((Number)obj).toString(),length);
			return;
		}

		throw new IllegalArgumentException("Argument obj is invalid!");

	}

	protected void writeChars(Object obj,int length) throws IOException
	{
		String s=(String)obj;
		writeDOSString(s,length);
	}


/**
 * Выводит в файл байт представляющий логический тип DBF. Либо NULL если поле не инициализировано
 * @param obj - обьект класса <code>java.lang.Boolean</code>. Если параметр равен NULL то выводится код '?'
 */

	protected void writeBoolean(Object obj) throws IOException
	{
		if (obj==null) RAF.writeByte(0x3F);
		Boolean b=(Boolean)obj;
		if (b.booleanValue()) RAF.writeByte(0x54);
		else RAF.writeByte(0x46);
	}

	protected void writeDate(Object obj) throws IOException
	{
		// ?????????????/
		if (obj==null) writeDOSString("",1);
		else writeDOSString("",1);
	}

/**
 * Добавление столбца
 * @return true - если успешно, false - если указаны неверно
 * параметры столбца либо уже были сформированы несколько
 * записей
 */

	public boolean addColumn(String name,char typeField,int length)
	{
		if (!columnsClosed){
			FieldDefs.add(new DBFFieldDef(name,typeField,length,0));
			recordLength+=length;
			columnsCount++;
			return true;
		}
		else return false;
	}


/**
 * Добавление записи
 * @param columns - массив обьектов, представляющий DBF строку. Элементы должны следовать в
 * том порядке, в котором они задавались в addColumn;
 * @return true - если успешно, false - если количество columns не совпадает с заданным в addColumn
 * либо тип Java аргумента не удается преобразовать к одному из типов DBF документа;
 */
	public boolean addRow(Object[] columns) throws IOException
	{
		if (!columnsClosed)
		{
			writeHeader();
		}
		columnsClosed=true;

		if (columns.length==columnsCount)
		{
			RAF.writeByte(DBF_RECORD.NORMAL);
			Iterator Itr=FieldDefs.iterator();
			for(int i=0;i<columnsCount;i++)
			{
				Object obj=Itr.next();
				int typeField=((DBFFieldDef)obj).getTypeField();
				switch(typeField)
				{
					case DBF_TYPES.CHAR:
						writeChars(columns[i],((DBFFieldDef)obj).getLength());
						break;
					case DBF_TYPES.NUMBER:
						writeNumber(columns[i],((DBFFieldDef)obj).getLength());
						break;
				}
			}
			recordsCount++;
			return true;
		}
		else return false;
	}
}


