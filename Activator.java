/*    */ package org.joget.sample;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import org.osgi.framework.BundleActivator;
/*    */ import org.osgi.framework.BundleContext;
/*    */ import org.osgi.framework.ServiceRegistration;
/*    */ 
/*    */ 
/*    */ public class Activator
/*    */   implements BundleActivator
/*    */ {
/*    */   protected Collection<ServiceRegistration> registrationList;
/*    */   
/*    */   public void start(BundleContext context) {
/* 16 */     this.registrationList = new ArrayList<>();
/*    */ 
/*    */     
/* 19 */     this.registrationList.add(context.registerService(ProgressBarFormatter.class.getName(), new ProgressBarFormatter(), null));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void stop(BundleContext context) {
/* 25 */     for (ServiceRegistration registration : this.registrationList)
/* 26 */       registration.unregister(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Hassam Tahir\Downloads\sample-plugins-1.0-SNAPSHOT.jar!\org\joget\sample\Activator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */