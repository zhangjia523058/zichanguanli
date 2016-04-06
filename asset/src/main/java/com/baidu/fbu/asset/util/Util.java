package com.baidu.fbu.asset.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import net.sf.json.JSONObject;

// +"-"+
// for( Iterator it = list.iterator(); it.hasNext(); ){ it.next(); }
// for( int i = 0; i < arr.length; i++ ) { }
public class Util {
    public static void print() {
        System.out.println();
    }

    public static void print(int s) {
        System.out.println(s);
    }

    public static void print(long s) {
        System.out.println(s);
    }

    public static void print(Object s) {
        System.out.println(s);
    }

    public static void printList(List list) { // the list contains maps
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Map map = (Map) it.next();

            Set key = map.keySet();
            for (Iterator ite = key.iterator(); ite.hasNext();) {
                String str = (String) ite.next();
                System.out.print(str + " : " + map.get(str) + "  ");
            }
            print();
        }
    }

    public static void print(Object a[]) {
        for (int i = 0; i < a.length; i++) {
            print(a[i] + " ");
        }
        print();
    }

    public static void print(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            print(arr[i] + "\t");
        }
        print();
    }

    public static void printListMap(List list) { // list 里放的是一个一个 Map
        for( Iterator it = list.iterator(); it.hasNext(); ){
            Map map = (Map) it.next();
            printMap(map);
        }
    }

    public static void printMap(Map map) {
        Set keys = map.keySet();
        for (Iterator it = keys.iterator(); it.hasNext();) {
            String key = (String) it.next();
            print(key + " : " + map.get(key) + "\t");
        }
        print();
    }

    public static StringBuffer getStringBuffer() {
        return new StringBuffer();
    }

    public static StringBuffer getStringBuffer(String st) {
        return new StringBuffer(st);
    }

    public static ArrayList<Object> getArrayList() {
        return new ArrayList<Object>();
    }

    public static LinkedList<Object> getLinkedList() {
        return new LinkedList<Object>();
    }

    public static HashMap<String, Object> getHashMap() {
        return new HashMap<String, Object>();
    }

    public static HashMap<String, Object> getLinkedHashMap() {
        return new LinkedHashMap<String, Object>();
    }

    // public static HashMap<String, Object> getHashMap( String key, Object value ) {
    //    HashMap<String, Object> map = new HashMap<String, Object>();
    //    map.put( key, value );
    //    return map;
    // }

    public static TreeMap<String, Object> getTreeMap() {
        return new TreeMap<String, Object>();
    }

    public static TreeSet getTreeSet() {
        return new TreeSet();
    }

    public static HashSet getHashSet() {
        return new HashSet();
    }

    // copy a hashMap
    // test it
    /* Map map3 = copyMap( map1 ); print( map1.equals( map3 ) );*/
    public static HashMap copyMap(Map map) {
        return new HashMap(map);
   }

    /** 复制集合 */
    public static List copyList(List list) {
        // return getPartOfAList( list, 0, list.size()-1 );
        List result = Util.getArrayList();
        result.addAll(list);
        return result;
    }

    // 复制对象 用序列化的方法
    public static Object copyObject(Object obj) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(obj);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            return in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 判断 String 或 Collection 是不是null 或 空字符串 空结果集
    public static boolean isNullOrEmptyOrZero(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String) {
            String st = (String) obj;
            if (st.equals("")) {
                return true;
            } else {
                return false;
            }
        } else if (obj instanceof StringBuffer) {
            StringBuffer st = (StringBuffer) obj;
            if (st.toString().equals("")) {
                return true;
            } else {
                return false;
            }
        } else if (obj instanceof Collection) {
            Collection c = (Collection) obj;
            if (c.size() == 0) {
                return true;
            } else if (c.size() == 1) {
                Iterator it = c.iterator();
                if (it.next() == null) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (obj instanceof Integer) {
            int temp = (Integer) obj;
            if (temp == 0) {
                return true;
            } else {
                return false;
            }
        } else if (obj instanceof Long) {
            long temp = (Long) obj;
            if (temp == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            // print( " ----- 输入参数的类型是 "+obj.getClass().getCanonicalName()
            // +"   请输入 String / StringBuffer / Collection / Integer / Long " );
            return false;
        }
    }

    public static void sleep(int sec) {
        // Thread.sleep( sec * 1000 );
        // TimeUnit.MILLISECONDS.sleep( sec );
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** 获得当前的时间戳，返回字符串 */
    public static String getStringTime() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return fmt.format(new Date());
    }

    /** 遍历实体类的属性和数据类型以及属性值 */
    public static void reflectTest( Object model ) throws NoSuchMethodException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        print("类名：" + model.getClass().getName() );
        // 获取实体类的所有属性，返回 Field 数组
        Field[] field = model.getClass().getDeclaredFields();

        // 遍历所有属性
        for(int j = 0; j < field.length; j++){
            String name = field[j].getName();  // 获取属性的名字
            print("属性：" + name);

            String type = field[j].getGenericType().toString();  // 获取属性的类型

            if(type.equals("class java.lang.String") ){
                print("类型：String");
            }else if(type.equals("class java.util.Date") ){
                print("类型：Date");
            }else if(type.equals("class java.lang.Integer") || type.equals("int") ){
                print("类型：Integer or int");
            }else if(type.equals("class java.lang.Short") || type.equals("short") ){
                print("类型：Short or short");
            }else if( type.equals("class java.lang.Byte") || type.equals("byte") ){
                print("类型：Byte or byte ");
            }else if(type.equals("class java.lang.Long") || type.equals("long") ){
                print("类型：Long or long");
            }else if( type.equals("class java.lang.Character") || type.equals("char") ){
                print("类型：Character or char");
            }else if(type.equals("class java.lang.Float") || type.equals("float") ){
                print("类型：Float or float");
            }else if( type.equals("class java.lang.Double") || type.equals("double") ){
                print("类型：Double or double");
            }else if (type.equals("class java.lang.Boolean") || type.equals("boolean") ) {
                print("类型：Boolean or boolean");
            }

            Method m = model.getClass().getMethod(   // 调用 getter 方法获取属性值
                    "get" + name.substring(0, 1).toUpperCase() + name.substring(1) );

            Object value = m.invoke(model);

            if (value != null) {
                print("属性值为：" + value);
            } else {
                print("属性值为：空");
            }

        }
    }


    public static List arrayToList( Object arr ){
        return Arrays.asList( arr );
    }

    public static Object[] listToArray( List list ){
        return list.toArray();
    }

    // input a string of ids like "1,2,3,4", return a list containing ids of integer format
    public static List<Object> idsStringToList( String idsString ) {
        String[] idArray;

        if( idsString.equals("") || idsString == null ){
            idArray = new String[0];
        }else{
            idArray = idsString.split(",");    // 分割  id 字符串  "id1,id2,id3"
        }

        List<Object> idList = Util.getArrayList();

        for( int i = 0; i<idArray.length; i++ ){
            idList.add( idArray[i] );
        }

        return idList;
    }

    //
    public static JSONObject jsonStringToJsonObject( String jsonString ){
        return JSONObject.fromObject( jsonString );
    }


/////////////// snippet //////////////
    public static void join2Maps( ){
        Map map1 = Util.getHashMap();
        Map map2 = Util.getHashMap();
        map1.put(1, 1);
        map1.put(2, 2);

        map2.put(2, 22);
        map2.put(3, 3);

        map1.putAll( map2 );   // 合并两个 map  覆盖相同的 key
        print( map1 );

    }


    public static Map getOperateFee( String fullCutLimit ) {
        Map result = new HashMap();
        JSONObject jObj = JSONObject.fromObject( fullCutLimit );

        for( Object object : jObj.entrySet() ){
            Entry entry = (Entry)object;
            String key = (String)entry.getKey();
            String value = (String)entry.getValue();

            String[] keyArr = key.split("-");
            String[] valueArr = value.split("-");

            // print( "KEY:"+key+"  -->  Value:"+value );
            // JsonUtil.print( keyArr[0]+" -- "+keyArr[1]+" -- " +valueArr[0]+" -- "+ valueArr[1] );

            String key1 = "immediate";  // 支付立减
            String key2 = "full";       // 支付满减
            String operation = "cut";   // 减

            if( keyArr[0].equals( key1.toLowerCase() ) && valueArr[0].equals( operation.toLowerCase() )){
                result.put( keyArr[0], valueArr[1] );
            }else if( keyArr[0].equals( key2.toLowerCase() ) && valueArr[0].equals( operation.toLowerCase() )){
                result.put( keyArr[0], valueArr[1] );
            }
        }
        //JsonUtil.print( result );
        return result;
    }

    public static int jsonToMapTest( ){
        int strategyType = 2;    // 1:支付立减    2:支付满减     3:红包    4:支付折扣
        String fullCutLimit = "{\"immediate-0\":\"cut-3\",\"full-100\":\"cut-5\"}";  //存储 json 字符串

        Map fee = getOperateFee( fullCutLimit );

        String key1 = "immediate";
        String key2 = "full";

        int operateFee = 0;

        if( strategyType == 1 ){  // 1:支付立减
            operateFee = Integer.parseInt( fee.get( key1 ).toString() );
        }else if( strategyType == 2 ){   // 2:支付满减
            operateFee = Integer.parseInt(  fee.get( key2 ).toString() );
        }

        print( operateFee );

        return operateFee;

    }

    // 获得当前的时间字符串 Date
    public static String getDateStringOfToday() {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
        return df.format( today );
    }

    public static String formatDateTime(Date date) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
            return df.format( date );
        }
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
            return df.format( date );
        }
    }

    // 生成随机字符串的长度
    public static String getRandomString( int length ) {    // length 表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sbf.append(base.charAt(number));
        }
        return sbf.toString();
     }

///////////////////////////////
    public static void main(String[] args) throws Exception {
/*        Map map1 = Util.getHashMap();
        Map map2 = Util.getHashMap();
        map1.put(1, 1);
        map1.put(2, 2);

        map2.put(2, 22);
        map2.put(3, 3);

        map1.putAll( map2 );   // 合并两个 map
        print( map1 );


        String str = "{ \"userName\":\"jw\",  \"age\":20 }";
        Util.print( Util.jsonStringToJsonObject( str ) );      */

      //  jsonToMapTest( );

     /*   List list = null;
        list.size();   //  java.lang.NullPointerException
               */

        String key = "dateFormatter";
        Date date = null; // new Date();

        if (key.equals("dateFormatter")) {
            String str = formatDate(date);
            print(str);
        } else {
            print(date);
        }




    }
}