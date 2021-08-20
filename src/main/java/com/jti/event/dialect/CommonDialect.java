package com.jti.event.dialect;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

import java.util.HashSet;
import java.util.Set;

@Component
public class CommonDialect extends AbstractDialect implements IExpressionObjectDialect {

    private static final String COMMON_UTIL_EXPRESSION_NAME = "tck";
    private static final String PAGEING_UTIL_EXPRESSION_NAME = "paginationInfo";
    
	CommonDialect() {
        super("Common Dialect");
    }
	
	 private static final Set<String> ALL_EXPRESSION_NAMES = new HashSet<String>(){
         {
        	 add(COMMON_UTIL_EXPRESSION_NAME);
        	 add(PAGEING_UTIL_EXPRESSION_NAME);
         }
     };

     @Override
     public IExpressionObjectFactory getExpressionObjectFactory() {
         return new IExpressionObjectFactory() {
             @Override
             public Set<String> getAllExpressionObjectNames() {
                 return ALL_EXPRESSION_NAMES;
             }

             @Override
             public Object buildObject(IExpressionContext context, String expressionObjectName) {
                 if(expressionObjectName.equals(COMMON_UTIL_EXPRESSION_NAME)){
                     return new CommonUtil();
                 }
                 else if(expressionObjectName.equals(PAGEING_UTIL_EXPRESSION_NAME)){
                     return new PaginationRenderer();
                 }
                 return null;
             }

             @Override
             public boolean isCacheable(String expressionObjectName) {
                 return false;
             }
         };         
     }
}