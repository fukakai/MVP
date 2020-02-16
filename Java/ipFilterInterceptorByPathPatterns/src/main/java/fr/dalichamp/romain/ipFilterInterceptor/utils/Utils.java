package fr.dalichamp.romain.ipFilterInterceptor.utils;

import java.util.Set;

/**
 * @author Romain DALICHAMP - romain.dalichamp@gmail.com
 * <p>
 * Utils
 */
public class Utils {

    public static boolean isElementInSetList(Set setList,String element) {
        if(setList.contains(element)) return true;
        return false;
    }
}
