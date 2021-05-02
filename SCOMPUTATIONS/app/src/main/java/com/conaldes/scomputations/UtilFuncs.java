package com.conaldes.scomputations;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import static java.lang.Character.isDigit;

/**
 *
 * @author hp
 */
public class UtilFuncs {
    public static List<String> getUsrInfolst(String uinfosStr){
        List<String> strlst = new ArrayList<>();
        uinfosStr = uinfosStr.trim();
        StringTokenizer strtk = new StringTokenizer(uinfosStr, "=;");
        if(uinfosStr.indexOf("schedule", 0) != -1) {
            int cnt = 0;
            while (strtk.hasMoreTokens()) {
                cnt = cnt + 1;
                String key = strtk.nextToken();
                String val = strtk.nextToken();
                switch (cnt) {
                    case 2:
                        strlst.add(val);
                        break;
                    case 3:
                        strlst.add(val);
                        break;
                    case 4:
                        strlst.add(val);
                        break;
                    default:
                        break;
                }
            }
        }else if(uinfosStr.indexOf("response4", 0) != -1){
            int cnt = 0;
            while(strtk.hasMoreTokens()){
                cnt = cnt + 1;
                String key = strtk.nextToken();
                String val = strtk.nextToken();
                switch (cnt) {
                    case 2:
                        strlst.add(val);
                        break;
                    //case 3:
                    //    strlst.add(val);
                    //    break;
                    default:
                        break;
                }
            }
        } else if(uinfosStr.indexOf("login6", 0) != -1){
            int cnt = 0;
            while(strtk.hasMoreTokens()){
                cnt = cnt + 1;
                String key = strtk.nextToken();
                String val = strtk.nextToken();
                if(cnt == 2){
                    strlst.add(val);
                    System.out.println("val (cnt == 2): " +  val);
                }
            }
        }
        return strlst;
    }

    public static List<String> getStrList(String strtoserver) {
        List<String> strlst = new ArrayList<>();
        strtoserver = strtoserver.trim();
        StringTokenizer strtk = new StringTokenizer(strtoserver, "=;");
        while (strtk.hasMoreTokens()) {
            String key = strtk.nextToken();
            String val = strtk.nextToken();
            strlst.add(val);
        }
        return strlst;
    }

    public static String get3Codes(String uinfosStr){
        String codesstr = "";
        uinfosStr = uinfosStr.trim();
        StringTokenizer strtk = new StringTokenizer(uinfosStr, "=;");
        if(uinfosStr.indexOf("login6", 0) != -1){
            int cnt = 0;
            while(strtk.hasMoreTokens()){
                cnt = cnt + 1;
                String key = strtk.nextToken();
                String val = strtk.nextToken();
                if(cnt == 4) {
                    codesstr = val;
                    System.out.println("val (cnt == 4): " +  val);
                }
            }
        }
        return codesstr;
    }

    public static String[] getStringArray(String jscriptStr) {
        List<String> strlst = new ArrayList<>();
        jscriptStr = jscriptStr.trim();
        String[] stringArray = null;
        StringTokenizer strtk = new StringTokenizer(jscriptStr, "=;");
        while (strtk.hasMoreTokens()) {
            String key = strtk.nextToken();
            String val = strtk.nextToken();
            //System.out.println("key, val: " + key + ", " + val);
            strlst.add(val);
        }
        stringArray = new String[strlst.size()];
        int i = 0;
        for (String hdstr : strlst) {
            stringArray[i] = hdstr;
            //System.out.println("stringArray[" + i + "]: " + stringArray[i]);
            i++;
        }
        return stringArray;
    }

    public static String[] resetStringArray(String[] codesarr, String tskcodeStr) {
        List<String> strlst = new ArrayList<>();
        tskcodeStr = tskcodeStr.trim();
        if(codesarr != null){
            for (int i = 0; i < codesarr.length; i++) {
                strlst.add(codesarr[i]);
                System.out.println("codesarr[" + i + "]: " + codesarr[i]);
            }
        }
        if(!strlst.contains(tskcodeStr))
            strlst.add(tskcodeStr);
        String[] stringArray = new String[strlst.size()];
        int i = 0;
        for (String hdstr : strlst) {
            stringArray[i] = hdstr;
            System.out.println("newstringArray[" + i + "]: " + stringArray[i]);
            i++;
        }
        return stringArray;
    }

    public static boolean userLoggedIn(String uinfosStr){
        if(uinfosStr.indexOf("authentication", 0) != -1){
            return true;
        } else {
            return false;
        }
    }

    public static List<String> getSTRItemLst(String scharsepStr){
        System.out.println("scharsepStr: " +  scharsepStr);
        List<String> strList = new ArrayList<>();
        int i = 0;
        int k = 0;
        while(k < (scharsepStr.length() - 1)){
            i = scharsepStr.indexOf("&", k);
            String strItem = "";
            if((i != -1) && k < scharsepStr.length()){
                strItem = scharsepStr.substring(k, i);
                strList.add(strItem);
                System.out.println("strItem: " + strItem);
            }else if ((i == -1) && k < scharsepStr.length()){
                strItem = scharsepStr.substring(k);
                strList.add(strItem);
                System.out.println("strItem: " + strItem);
                break;
            }
            k = i + 1;
        }
        //"xxx"|"zzz"|"kkk"|"hgt"|"opi"|"bgh"
        return strList;
    }

    public static boolean isRiderCode(String codestr, String codesstr){
        codesstr = codesstr.trim();
        codestr = codestr.trim();
        int k = codesstr.indexOf("#", 0);
        k++;
        int l = codesstr.indexOf("#", k);
        l++;
        int m = codesstr.indexOf("#", l);
        String ucode = codesstr.substring(k, l - 1);
        if(codestr.equals(ucode))
            return true;
        else
            return false;
    }

    public static String firstCode(String codesstr){
        codesstr = codesstr.trim();
        int j = codesstr.indexOf("#", 0);
        String ucode = codesstr.substring(0, j);
        return ucode;
    }

    public static String secondCode(String codesstr){
        codesstr = codesstr.trim();
        int k = codesstr.indexOf("#", 0);
        k++;
        int l = codesstr.indexOf("#", k);
        l++;
        int m = codesstr.indexOf("#", l);
        String ucode = codesstr.substring(k, l - 1);
        return ucode;
    }

    public static String thirdCode(String codesstr){
        codesstr = codesstr.trim();
        int k = codesstr.indexOf("#", 0);
        k++;
        int l = codesstr.indexOf("#", k);
        l++;
        int m = codesstr.indexOf("#", l);
        String ucode = codesstr.substring(m + 1);
        return ucode;
    }

    public static String capitaliseFirstLetter(String inputStr){
        System.out.println("inputStr: " +  inputStr);
        StringBuilder sb = new StringBuilder();
        inputStr = inputStr.trim();
        if(inputStr.equals("USA"))
            return "USA";
        if(inputStr.equals("UK"))
            return "UK";
        sb.append("");
        int ln = inputStr.length();
        System.out.println("inputStr.length(): " + inputStr.length());
        int n = 0;
        int mk = 0;
        StringBuilder bd = new StringBuilder();
        bd.append("");
        char c = inputStr.charAt(0);
        bd.append(c);
        bd.append("");
        String str = bd.toString().toUpperCase();
        sb.append(str);
        n++;
        while(n < ln){
            if(inputStr.charAt(n) == ' '){
                bd = new StringBuilder();
                sb.append(" ");
                n++;
                bd.append("");
                c = inputStr.charAt(n);
                bd.append(c);
                bd.append("");
                str = bd.toString().toUpperCase();
                sb.append(str);
                n++;
            }else if(inputStr.charAt(n) == '-'){
                bd = new StringBuilder();
                c = inputStr.charAt(n);
                sb.append(c);
                n++;
                bd.append("");
                c = inputStr.charAt(n);
                bd.append(c);
                bd.append("");
                str = bd.toString().toUpperCase();
                sb.append(str);
                n++;
            }else if(inputStr.charAt(n) == ','){
                System.out.println("inputStr.charAt(n): " + inputStr.charAt(n));
                //bd = new StringBuilder();
                c = inputStr.charAt(n);
                sb.append(c);
                n++;
                if(inputStr.charAt(n) == ' '){     // || (inputStr.charAt(n) == '-') || (inputStr.charAt(n) == ',')){
                    bd = new StringBuilder();
                    sb.append(" ");
                    n++;
                    bd.append("");
                    c = inputStr.charAt(n);
                    bd.append(c);
                    bd.append("");
                    str = bd.toString().toUpperCase();
                    sb.append(str);
                    n++;
                }
            }else if((inputStr.charAt(n) != ' ') && (inputStr.charAt(n) != '-') && (inputStr.charAt(n) != ',')){
                bd = new StringBuilder();
                bd.append("");
                c = inputStr.charAt(n);
                bd.append(c);
                bd.append("");
                str = bd.toString().toLowerCase();
                sb.append(str);
                n++;
            }
        }
        sb.append("");
        System.out.println("sb.toString(): " + sb.toString());
        return sb.toString();
    }

    public static String[] barDelstringToArray(String dataStr){
        String scharsepStr = "";
        String[] strarray = null;
        try{
            scharsepStr = dataStr.substring(0, dataStr.length() -1);
            int ln = scharsepStr.trim().length();
            int n = 0;
            int sn = 0;
            while(n < ln){
                if(scharsepStr.charAt(n) == '|') {
                    sn++;
                }
                n++;
            }
            //System.out.println("no. of |: " +  sn);
            if(sn == 1){
                strarray = new String[2];
                //System.out.println("strarray.length: " +  strarray.length);
                int ii = scharsepStr.indexOf("|", 0);
                String itemstr0 = scharsepStr.substring(0, ii);
                String itemstr1 = scharsepStr.substring(ii + 1);
                strarray[0] = itemstr0;
                strarray[1] = itemstr1;
            }else{
                strarray = new String[sn + 1];
                //System.out.println("strarray.length: " +  strarray.length);
                int i = 0;
                int j = 0;
                int k = 0;
                while(k < ln){
                    i = scharsepStr.indexOf("|", k);
                    String strItem = "";
                    if((i != -1) && k < ln){
                        strItem = scharsepStr.substring(k, i);
                        strarray[j] = strItem;
                        //System.out.println("strarray[" + j + "]: " +  strarray[j]);
                        j++;
                    }else if ((i == -1) && k < ln){
                        strItem = scharsepStr.substring(k);
                        strarray[j] = strItem;
                        //System.out.println("strarray[" + j + "]: " +  strarray[j]);
                        break;
                    }
                    k = i + 1;
                }
            }
        }catch (NullPointerException ex) {
            strarray = new String[1];
            strarray[0] = "none";
            return strarray;
        }catch (ArrayIndexOutOfBoundsException ex) {
            strarray = new String[1];
            strarray[0] = "none";
            return strarray;
        }catch (StringIndexOutOfBoundsException ex) {
            strarray = new String[1];
            strarray[0] = "none";
            return strarray;
        }
        return strarray;
    }

    public static String[] stringToArray(String scharsepStr){
        System.out.println("scharsepStr: " +  scharsepStr);
        int ln = scharsepStr.trim().length();
        int n = 0;
        int sn = 0;
        while(n < ln){
            if(scharsepStr.charAt(n) == '|')
                sn++;
            n++;
        }
        System.out.println("no. of |: " +  sn);
        String[] strarray = new String[sn + 1];
        System.out.println("strarray.length: " +  strarray.length);
        int i = 0;
        int j = 0;
        int k = 0;
        while(k < ln){
            i = scharsepStr.indexOf("|", k);
            String strItem = "";
            if((i != -1) && k < ln){
                strItem = scharsepStr.substring(k, i);
                strarray[j] = strItem;
                System.out.println("strarray[" + j + "]: " +  strarray[j]);
                j++;
            }else if ((i == -1) && k < ln){
                strItem = scharsepStr.substring(k);
                strarray[j] = strItem;
                System.out.println("strarray[" + j + "]: " +  strarray[j]);
                break;
            }
            k = i + 1;
        }
        //"usrlogon|" + categ + "|" + email + "|" + psswd + ""
        //"xxx|zzz|kkk|hgt|opi|bgh"
        return strarray;
    }

    public static String getServerMsg(String smsgStr){
        smsgStr = smsgStr.trim();
        if(smsgStr.isEmpty())
            return "";
        String msgStr = "";
        StringTokenizer strtk = new StringTokenizer(smsgStr, "=;");
        if(smsgStr.indexOf("toclient", 0) != -1){
            int cnt = 0;
            while(strtk.hasMoreTokens()){
                cnt = cnt + 1;
                String key = strtk.nextToken();
                String val = strtk.nextToken();
                if(cnt > 1) {
                    msgStr = msgStr + key + ": " + val + "\n";
                }
            }
        }else if(smsgStr.indexOf("schedule", 0) != -1){
            int cnt = 0;
            while(strtk.hasMoreTokens()){
                cnt = cnt + 1;
                String key = strtk.nextToken();
                String val = strtk.nextToken();
                switch (cnt) {
                    case 2:
                        msgStr = msgStr + "Task code: " + val + "\n";
                        msgStr = msgStr + "Task Schedule and Routing on Google Map";
                        break;
                    //case 5:
                    //    msgStr = msgStr + "Client name: " + val + "\n";
                    //    break;
                    //case 6:
                    //    msgStr = msgStr + "Rider name: " + val + "\n";
                    //    break;
                    //case 7:
                    //    msgStr = msgStr + "Location 1: " + val + "\n";
                    //    break;
                    //case 8:
                    //    msgStr = msgStr + "Location 2: " + val + "\n";
                    //    break;
                    //case 9:
                    //    msgStr = msgStr + "Details: " + val;        // + "\n";
                    //    break;
                    default:
                        break;
                }
            }
        } else if(smsgStr.indexOf("login6", 0) != -1){
            int cnt = 0;
            while(strtk.hasMoreTokens()){
                cnt = cnt + 1;
                String key = strtk.nextToken();
                String val = strtk.nextToken();
                switch (cnt) {
                    case 2:
                        msgStr = msgStr + key + ": " + val + "\n";
                        break;
                    case 3:
                        msgStr = msgStr + key + ": " + val + "\n";
                        break;
                    case 5:
                        msgStr = msgStr + key + ": " + val;   // + "\n";
                        break;
                    default:
                        break;
                }
            }
        } else if(smsgStr.indexOf("login1", 0) != -1){
            int cnt = 0;
            while(strtk.hasMoreTokens()){
                cnt = cnt + 1;
                String key = strtk.nextToken();
                String val = strtk.nextToken();
                if(cnt == 2)
                    msgStr = msgStr + key + ": " + val + "\n";
            }
        } else if(smsgStr.indexOf("login4", 0) != -1){
            int cnt = 0;
            while(strtk.hasMoreTokens()){
                cnt = cnt + 1;
                String key = strtk.nextToken();
                String val = strtk.nextToken();
                switch (cnt) {
                    case 2:
                        msgStr = msgStr + key + ": " + val + "\n";
                        break;
                    case 3:
                        msgStr = msgStr + key + ": " + val + "\n";
                        break;
                    case 4:
                        msgStr = msgStr + key + ": " + val;   // + "\n";
                        break;
                    default:
                        break;
                }
            }
        } else if(smsgStr.indexOf("login5", 0) != -1){
            int cnt = 0;
            while(strtk.hasMoreTokens()){
                cnt = cnt + 1;
                String key = strtk.nextToken();
                String val = strtk.nextToken();
                switch (cnt) {
                    case 2:
                        msgStr = msgStr + key + ": " + val + "\n";
                        break;
                    case 3:
                        msgStr = msgStr + key + ": " + val + "\n";
                        break;
                    case 4:
                        msgStr = msgStr + key + ": " + val + "\n";
                        break;
                    case 5:
                        msgStr = msgStr + key + ": " + val;   // + "\n";
                        break;
                    default:
                        break;
                }
            }
        } else if(smsgStr.indexOf("routerider", 0) != -1){
            int cnt = 0;
            while(strtk.hasMoreTokens()){
                cnt = cnt + 1;
                String key = strtk.nextToken();
                String val = strtk.nextToken();
                switch (cnt) {
                    case 2:
                        msgStr = msgStr + key + ": " + val + "\n";
                        break;
                    case 3:
                        msgStr = msgStr + key + ": " + val + "\n";
                        break;
                    case 4:
                        msgStr = msgStr + key + ": " + val + "\n";
                        break;
                    case 5:
                        msgStr = msgStr + key + ": " + val + "\n";
                        break;
                    case 6:
                        msgStr = msgStr + key + ": " + val;   // + "\n";
                        break;
                    default:
                        break;
                }
            }
        }
        return msgStr;
    }
    //routerider=routerider;taskcode=undefined;ridercode=undefined;colladdress=;delvaddress=;tripdistance=

    public static String getRoutingMsg(String smsgStr){
        smsgStr = smsgStr.trim();
        if(smsgStr.isEmpty())
            return "";
        String str = "";
        StringTokenizer strtk = new StringTokenizer(smsgStr, "=;");
        //  svmsg = "schedule=Task Schedule;taskcode=" + tcodestr + ";clrdcode1=" + ucode1str + ";clrdcode2=" + ucode2str + ";client=" + names1str + ";rider=" + names2str + ";location1=" + locatn1str + ";location2=" + locatn2str + ";details=" + detailsstr;
        if(smsgStr.indexOf("schedule", 0) != -1){
            int cnt = 0;
            while(strtk.hasMoreTokens()){
                cnt = cnt + 1;
                String key = strtk.nextToken();
                String val = strtk.nextToken();
                switch (cnt) {
                    case 2:
                        str += val;
                        break;
                    case 4:
                        str += "|" + val ;
                        break;
                    case 5:
                        str += "|" + val ;
                        break;
                    case 6:
                        str += "|" + val ;
                        break;
                    case 7:
                        str += "|" + val ;
                        break;
                    case 8:
                        str += "|" + val ;
                        break;
                    case 9:
                        str += "|" + val ;
                        break;
                    default:
                        break;
                }
            }
        }
        return str;
    }

    public static String getActionSTR(String smsgStr){
        smsgStr = smsgStr.trim();
        if(smsgStr.isEmpty())
            return "";
        String msgStr = "";
        StringTokenizer strtk = new StringTokenizer(smsgStr, "=;");
        while(strtk.hasMoreTokens()){
            String key = strtk.nextToken();
            String val = strtk.nextToken();
            msgStr = msgStr + key + ": " + val + "\n";
        }
        return msgStr;
    }

    public static String removeLRQDQuotes(String smsgStr){
        smsgStr = smsgStr.trim();
        if(smsgStr.isEmpty())
            return "";
        String msgStr = "";
        int strln = smsgStr.length();
        if((smsgStr.charAt(0) == '"') && (smsgStr.charAt(strln - 1) == '"'))
            msgStr = smsgStr.substring(1, (strln - 1));
        return msgStr;
    }

    public static byte[] imageBytes(String filename){
        File file = new File(filename.trim());
        FileInputStream fis = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(FrontDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            for(int readNum = 0; (readNum = fis.read(buf)) != -1;){
                bos.write(buf, 0, readNum);
            }
        } catch (IOException ex) {
            //Logger.getLogger(FrontDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bos.toByteArray();
    }

    /** Sorry for not using the standard org.json.JSONArray but even in Android 4.2 it lacks
     * the JSONArray(Object[]) constructor, making it too painful to use.
     * @param data
     * @return
     */
    public static String a1dToJson(String[] data) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        for (int i = 0; i < data.length; i++) {
            String str = data[i];
            if (i > 0)
                sb.append("|");
            sb.append(str);
        }
        sb.append("");
        System.out.println("sb.toString(): " +  sb.toString());
        return sb.toString();
    }

    public static String getDigitSequence(String deviceportstr) {
        StringBuilder sb = new StringBuilder();
        deviceportstr = deviceportstr.trim();
        int ln = deviceportstr.length();
        char c = 0;
        for(int i = 0; i < ln; i++){
            c = deviceportstr.charAt(i);
            if(isDigit(c)){
                sb.append(c);
            }
        }
        String mbname = sb.toString() + "_device_server";
        System.out.println("MailBoxName: " + mbname);
        return mbname;
    }

    public static String arrayToString(String[] data) {
        String str = "";
        int k = data.length;
        str = data[0];
        for (int i= 1 ; i < k; i++) {
            str += "|" + data[i] ;
        }
        return str;
    }

    private static String replaceNull(String s) {
        return s == null ? "" : s;
    }

    public static String noNull(String s) {
        return s == null ? "" : s;
    }

    public static boolean isANum(String hdstr) {
        hdstr = hdstr.trim();
        int slen = hdstr.length();
        for(int i = 0; i < slen; i++){
            char ch = hdstr.charAt(i);
            if(!Character.isDigit(ch))
                return false;
        }
        return true;
    }
}
