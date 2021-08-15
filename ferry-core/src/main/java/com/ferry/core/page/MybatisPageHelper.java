package com.ferry.core.page;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
 * @Author: 摆渡人
 * @Date: 2021/4/26
 */
public class MybatisPageHelper {

	public static final String findPage = "findPage";
	
	/**
	 * 分页查询, 约定查询方法名为 “findPage” 
	 * @param pageRequest 分页请求
	 * @param mapper Dao对象，MyBatis的 Mapper	
	 * @param
	 * @return
	 */
	public static PageResult findPage(PageRequest pageRequest, Object mapper) {
		return findPage(pageRequest, mapper, findPage);
	}
	
	/**
	 * 调用分页插件进行分页查询
	 * @param pageRequest 分页请求
	 * @param mapper Dao对象，MyBatis的 Mapper	
	 * @param queryMethodName 要分页的查询方法名
	 * @param args 方法参数
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static PageResult findPage(PageRequest pageRequest, Object mapper, String queryMethodName, Object... args) {
		// 设置分页参数
		int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		// 利用反射调用查询方法
		Object result = invoke(mapper, queryMethodName, args);
		return getPageResult(pageRequest, new PageInfo((List) result));
	}

	/**
	 * 将分页信息封装到统一的接口
	 * @param pageRequest 
	 * @param
	 * @return
	 */
	private static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
		PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
		return pageResult;
	}

	public static Object invoke(Object object, String method, Object... args) {
		Object result = null;
		Class<? extends Object> clazz = object.getClass();
		Method queryMethod = getMethod(clazz, method, args);
		if(queryMethod != null) {
			try {
				result = queryMethod.invoke(object, args);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			try {
				throw new NoSuchMethodException(clazz.getName() + " 类中没有找到 " + method + " 方法。");
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 根据方法名和参数对象查找方法
	 * @param clazz
	 * @param name
	 * @param args 参数实例数据
	 * @return
	 */
	public static Method getMethod(Class<? extends Object> clazz, String name, Object[] args) {
		Method queryMethod = null;
		Method[] methods = clazz.getMethods();
		for(Method method:methods) {
			if(method.getName().equals(name)) {
				Class<?>[] parameterTypes = method.getParameterTypes();
				if(parameterTypes.length == args.length) {
					boolean isSameMethod = true;
					for(int i=0; i<parameterTypes.length; i++) {
						Object arg = args[i];
						if(arg == null) {
							arg = "";
						}
						if(!parameterTypes[i].equals(args[i].getClass())) {
							isSameMethod = false;
						}
					}
					if(isSameMethod) {
						queryMethod = method;
						break ;
					}
				}
			}
		}
		return queryMethod;
	}
}
