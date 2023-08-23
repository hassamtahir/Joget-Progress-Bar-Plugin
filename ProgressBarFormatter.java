/*    */ package org.joget.sample;
/*    */ 
/*    */ import net.sf.json.JSONObject;
/*    */ import org.joget.apps.app.dao.EnvironmentVariableDao;
/*    */ import org.joget.apps.app.model.AppDefinition;
/*    */ import org.joget.apps.app.model.EnvironmentVariable;
/*    */ import org.joget.apps.app.service.AppPluginUtil;
/*    */ import org.joget.apps.app.service.AppUtil;
/*    */ import org.joget.apps.datalist.model.DataList;
/*    */ import org.joget.apps.datalist.model.DataListColumn;
/*    */ import org.joget.apps.datalist.model.DataListColumnFormat;
/*    */ import org.joget.apps.datalist.model.DataListColumnFormatDefault;
/*    */ import org.joget.commons.util.LogUtil;
/*    */ import org.joget.plugin.base.PluginManager;
/*    */ import org.joget.plugin.property.service.PropertyUtil;
/*    */ import org.springframework.beans.BeansException;
/*    */ 
/*    */ public class ProgressBarFormatter
/*    */   extends DataListColumnFormatDefault
/*    */ {
/*    */   private static final String MESSAGE_PATH = "messages/ProgressBarFormatter";
/*    */   
/*    */   public String getName() {
/* 24 */     return "File Link Datalist Formatter";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getVersion() {
/* 29 */     return "5.0.0";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getClassName() {
/* 34 */     return getClass().getName();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLabel() {
/* 40 */     return AppPluginUtil.getMessage("org.joget.tutorial.ProgressBarFormatter.pluginLabel", getClassName(), "messages/ProgressBarFormatter");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 46 */     return AppPluginUtil.getMessage("org.joget.tutorial.ProgressBarFormatter.pluginDesc", getClassName(), "messages/ProgressBarFormatter");
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPropertyOptions() {
/* 51 */     return AppUtil.readPluginResource(getClassName(), "/properties/progressBarFormatter.json", null, true, "messages/ProgressBarFormatter");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String format(DataList dataList, DataListColumn column, Object row, Object value) {
/*    */     try {
/* 58 */       String templateId = getPropertyString("templateId");
/* 59 */       PluginManager pluginManager = (PluginManager)AppUtil.getApplicationContext().getBean("pluginManager");
/* 60 */       AppDefinition appDef = AppUtil.getCurrentAppDefinition();
/* 61 */       EnvironmentVariableDao environmentVariableDao = (EnvironmentVariableDao)AppUtil.getApplicationContext().getBean("environmentVariableDao");
/* 62 */       EnvironmentVariable ev = (EnvironmentVariable)environmentVariableDao.loadById(templateId, appDef);
/*    */       
/* 64 */       JSONObject templateObject = new JSONObject(getRemarks());
/* 65 */       String className = (String)templateObject.get("publicClass");
/* 66 */       String pluginPropertiesJSON = ev.getValue();
/* 67 */       pluginPropertiesJSON = AppUtil.processHashVariable(pluginPropertiesJSON, null, null, null);
/*    */       
/* 69 */       DataListColumnFormat p = (DataListColumnFormat)pluginManager;
/* 70 */       p.setProperties(PropertyUtil.getPropertiesValueFromJson(pluginPropertiesJSON));
/*    */       
/* 72 */       float percentage = ((Float)value).floatValue();
/* 73 */       if (percentage == 100.0F) {
/* 74 */         return "<div class=\"progress\"><div class=\"progress-bar progress-bar-success progress-bar-striped\" role=\"progressbar\"aria-valuenow=\"100\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width:100%\">100% Complete</div></div>";
/*    */       }
/* 76 */       return "<div class=\"progress\">  <div class=\"progress-bar progress-bar-striped active\" role=\"progressbar\"  aria-valuenow=\"" + value + "\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width:" + value + "%\">    " + value + "%  </div></div>";
/* 77 */     } catch (BeansException ex) {
/* 78 */       LogUtil.error(getClass().getName(), (Throwable)ex, "Plugin ERR");
/* 79 */       return "";
/*    */     } 
/*    */   } private boolean getRemarks() {
/* 82 */     throw new UnsupportedOperationException("Not supported yet.");
/*    */   }
/*    */ }


/* Location:              C:\Users\Hassam Tahir\Downloads\sample-plugins-1.0-SNAPSHOT.jar!\org\joget\sample\ProgressBarFormatter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */