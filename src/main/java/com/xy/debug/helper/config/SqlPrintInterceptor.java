package com.xy.debug.helper.config;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.StringJoiner;

/**
 * 打印完整select SQL
 *
 * @author moxiaonan
 * @since 2021/5/8
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
@Component
public class SqlPrintInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        Configuration configuration = mappedStatement.getConfiguration();
        if (args.length > 1) {
            Object param = args[1];
            BoundSql boundSql = mappedStatement.getBoundSql(param);
            String sql = boundSql.getSql();
            // 获取元数据
            MetaObject metaObject = configuration.newMetaObject(boundSql.getParameterObject());
            for (ParameterMapping parameterMapping : boundSql.getParameterMappings()) {
                String property = parameterMapping.getProperty();
                String stringValue;
                if (metaObject.hasGetter(property)) {
                    // 通过元数据获取参数
                    stringValue = getParamString(metaObject.getValue(property));
                }else {
                    // 动态SQL的参数
                    stringValue = getParamString(boundSql.getAdditionalParameter(property));
                }
                sql = sql.replaceFirst("\\?",stringValue);
            }
            System.out.println(consoleHighlight(mappedStatement.getId() + " : \n" + sql));
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 参数变为字符串
     *
     * @param param
     * @return
     */
    public String getParamString(Object param){
        String val = "";
        StringJoiner sj = new StringJoiner("", "'", "'");
        if (param instanceof String) {
            val = sj.add((String)param).toString();
        }else if (null != param){
            val = param.toString();
        }
        return val;
    }

    /**
     * 控制台高亮颜色显示
     *
     * @param content
     * @return
     */
    public String consoleHighlight(String content){
        if (null != content && content.length() > 0) {
            StringJoiner colorJoiner = new StringJoiner("","\033[1;36m","\033[0m");
            return colorJoiner.add(content).toString();
        }
        return "";
    }
}
