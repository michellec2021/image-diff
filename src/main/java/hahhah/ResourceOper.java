package hahhah;

import com.google.common.io.Resources;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * @author Michelle.Chen
 */
public class ResourceOper {
    public static void main(String[] args) {
        String appPath = StringUtils.substringAfter(Resources.getResource("app").getFile(), "/");
        File file=new File(appPath);
        File[] files = file.listFiles();
        TreeSet<String> appNameSet=new TreeSet();
        for (File file1 : files) {
            appNameSet.add(file1.getName());
        }
        System.out.println("appNameSet = " + appNameSet);
        System.out.println("appNameSet.last() = " + appNameSet.last());

        //获得最新包
        String latest = appNameSet.last();

        //获得上一个版本+前几个版本
        appNameSet.remove(appNameSet.last());
        String lastApp = appNameSet.last();

        //获得前几个版本
        appNameSet.remove(appNameSet.last());

        boolean empty = appNameSet.isEmpty();
        int size = appNameSet.size();
        Iterator<String> iterator=appNameSet.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            System.out.println("next = " + next);
        }
    }

}
