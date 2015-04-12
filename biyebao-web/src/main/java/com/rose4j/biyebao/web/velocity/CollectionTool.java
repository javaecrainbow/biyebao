package com.rose4j.biyebao.web.velocity;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.tools.config.DefaultKey;

@DefaultKey("collectionTool")
public class CollectionTool {
	public static boolean isNotEmpty(Object o) {
		if(o instanceof Collection){
			return CollectionUtils.isNotEmpty((Collection)o);
		}else{
			if(o==null){
				return false;
			}else{
				return true;
			}
		}
	}
	public static boolean isEmpty(Object o) {
		return !isNotEmpty(o) ;
	}
}
