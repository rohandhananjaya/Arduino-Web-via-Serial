/*
 * Automatic Weather Station Broker v1.1
 * Developed by Rohan Dhananjaya Amarasooriya
 * techsayura.com
 */
package aws.broker;

import com.fazecast.jSerialComm.*;
import java.sql.*; 
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zeus
 */
public class AWSB extends javax.swing.JFrame {    

public String outputString;
    String txt;
    int portId=-1;
    boolean closePort=false;
    boolean online=false;
    
    String ofl_url="jdbc:mysql://localhost:3306/weather_data"; //Databsae URL
    String ofl_us="root"; // User
    String ofl_pw="";     // Password
    
    String onl_url="jdbc:mysql://online database if exist";
    String onl_us="user";
    String onl_pw="password";
    
    String run_url="";
    String run_us="";
    String run_pw="";
    /**
     * Creates new form AWSB
     */
    public AWSB() {
        
    initComponents();
    
    btnstp.setEnabled(false);
    SerialPort[] ports = SerialPort.getCommPorts();
    String[] result = new String[ports.length];
    for (int i = 0; i < ports.length; i++) {
        result[i] = ports[i].getSystemPortName();
        cmb1.addItem(result[i]);
    }        
        addRow("Start");
        if(online){
            run_url=onl_url;
            run_us=onl_us;
            run_pw=onl_pw;
        }else{
            run_url=ofl_url;
            run_us=ofl_us;
            run_pw=ofl_pw;            
        }
        chk_con();
    }
public void addRow(String log)
{
    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
    Object[] row ={log};
    tableModel.addRow(row);
    //table.setModel(tableModel);
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cmb1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnstrt = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnstp = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Weather Station Broker v1.1");
        setResizable(false);

        jLabel1.setText("Port :");

        btnstrt.setText("Start");
        btnstrt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnstrtActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Log"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
        }

        btnstp.setText("Stop");
        btnstp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnstpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnstrt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnstp))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnstrt)
                    .addComponent(btnstp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnstrtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnstrtActionPerformed
    portId=cmb1.getSelectedIndex();
    SerialPort comPort = SerialPort.getCommPorts()[portId];
    boolean res=comPort.openPort();
    
    addRow("Opening port "+comPort.getSystemPortName()+"..");
    if(res){
        addRow("Port opened..  Please wait");
        btnstrt.setEnabled(false);
        btnstp.setEnabled(true);
    }else{
        addRow(comPort.getSystemPortName()+" opening faild!");
    }
    comPort.addDataListener(new SerialPortDataListener() {
    
    char arr[];    
        
       @Override
       public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }
       @Override
       
       public void serialEvent(SerialPortEvent event)
       {
           
         try { Thread.sleep(5000); 
         
          byte[] newData = event.getReceivedData();
          
          for (int i = 0; i < newData.length; ++i){
              
             System.out.print((char)newData[i]);
             txt=txt+(char)newData[i];
              
            }
            if(txt.contains("null")==false){
                String frmt_txt=txt.trim();
                double tem,hu=0.00;
                if(frmt_txt.length()==10){
                    
                    addRow("Temperature :"+frmt_txt.substring(0, 5)+" Humidity :"+frmt_txt.substring(5, 10));
                    
                    tem= Double.valueOf(frmt_txt.substring(0, 5));
                    hu= Double.valueOf(frmt_txt.substring(5, 10));
                    update_data(tem,hu);
                    

                    
                }else{
                    addRow("Data not valid. Please wait..");
                }

            }           
            if(txt.contains("\n")){
                txt="";
            }
          
           }catch (Exception e) { e.printStackTrace(); 
                addRow(comPort.getSystemPortName()+" opening faild!");
           }
                if(closePort==true){
                    addRow(comPort.getSystemPortName()+" closed");
                    btnstrt.setEnabled(true);
                    btnstp.setEnabled(false);
                    closePort=false;
                    comPort.removeDataListener();
                    comPort.closePort();
                    
                }
       }

    }); 

    }//GEN-LAST:event_btnstrtActionPerformed

    private void btnstpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnstpActionPerformed
        closePort=true;
        SerialPort comPort = SerialPort.getCommPorts()[portId];
        addRow(comPort.getSystemPortName()+" closing");
    }//GEN-LAST:event_btnstpActionPerformed
    
    private void chk_con(){
        
        addRow("Checking server connection...");
        
        try{  
            
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection(run_url,run_us,run_pw);  
        Statement stmt=con.createStatement(); 
            addRow("Server is connection ok");
            if(cmb1.getItemCount()<=0){
                addRow("No ports detected!");
            }else{
                addRow("Select station port and press start");
            }
         con.close();
         
        }catch(Exception e)
        { btnstrt.setEnabled(false); 
            addRow("Error with server connection");
            System.out.println(e);}       
    }
    private void update_data(Double temp,Double hum){
    try{
        Date date = new Date();      
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(  
        "jdbc:mysql://localhost:3306/weather_data","root","");  
        Statement stmt = con.createStatement();
        stmt.executeUpdate("update w_data set time='"+new Timestamp(date.getTime())+"', hum="+hum+", temp="+temp);
        addRow("Data updated "+new Timestamp(date.getTime()));
        stmt.close();
        con.close();
                
    }catch(Exception e){
        System.out.print("Error 7 "+e.getMessage()+e.getLocalizedMessage());
        addRow("Data updated failed!");
    }        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AWSB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AWSB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AWSB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AWSB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AWSB().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnstp;
    private javax.swing.JButton btnstrt;
    private javax.swing.JComboBox<String> cmb1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
