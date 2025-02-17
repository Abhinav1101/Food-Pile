/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodpile;

import static foodpile.JdbcConnection.classForName;
import static foodpile.JdbcConnection.getConnection;
import static foodpile.JdbcConnection.password;
import static foodpile.JdbcConnection.username;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.sql.rowset.CachedRowSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author iayus
 */
public class Search extends javax.swing.JFrame {

    /**
     * Creates new form Search
     */
    public Search() {
        initComponents();
        sort();
        showall();
    }

    void showall() {
//        Connection con = null;
//        ResultSet rs = null;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            /*
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username, password);
            String query = "SELECT * FROM foodInventory WHERE username=? ";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, Login.loggedInUser);
            */
            
            Socket s = new Socket("localhost",6666);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF("populateTable");
            dout.writeUTF(Login.loggedInUser);
            dout.writeUTF("item_id");
            dout.writeUTF("null");
            dout.flush();

            ObjectInputStream oin = new ObjectInputStream(s.getInputStream());
            CachedRowSet result =  (CachedRowSet) oin.readObject();
            dout.close();
            s.close();

            while (result.next()) {
                int item_id = result.getInt("item_id");
                String category = result.getString("item_category");
                String name = result.getString("item_name");
                int quantity = result.getInt("item_quantity");
                int price = result.getInt("item_price");
                int th = result.getInt("threshold");
                model.addRow(new Object[]{item_id, name, category, quantity, th, price});
            }
            
        }catch (IOException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sort() {
        DefaultTableModel dm = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dm);
        table.setRowSorter(sorter);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        dashboard = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        categ = new javax.swing.JComboBox<>();
        category = new javax.swing.JLabel();
        display = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        start = new javax.swing.JTextField();
        display2 = new javax.swing.JButton();
        showall = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        dashboard.setText("Dashboard");
        dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        jLabel1.setText("Search & Sort");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(332, 332, 332)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(379, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dashboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Id", "Name", "Category", "Quantity", "Threshold", "Price"
            }
        ));
        jScrollPane1.setViewportView(table);

        categ.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cereals", "Fast Food", "Beverages", "Vegetables", "Fruits", "Dairy Products" }));

        category.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        category.setText("Category");

        display.setBackground(new java.awt.Color(255, 102, 255));
        display.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        display.setText("Display");
        display.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Starts with");

        display2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        display2.setText("Display");
        display2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                display2ActionPerformed(evt);
            }
        });

        showall.setBackground(new java.awt.Color(255, 102, 102));
        showall.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        showall.setText("Show All");
        showall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showallActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 945, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(category, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(categ, 0, 116, Short.MAX_VALUE)
                    .addComponent(start))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(display, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(display2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(showall, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(categ)
                    .addComponent(category, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(display, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(showall, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(start)
                    .addComponent(display2, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void displayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayActionPerformed
        // TODO add your handling code here:
//        Connection con = null;
//        ResultSet rs = null;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        String sort_order = (String) categ.getSelectedItem();
        try {
            /*
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username, password);
            String query = "SELECT * FROM foodInventory WHERE username=? and item_category=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, Login.loggedInUser);
            pst.setString(2, sort_order);
            */
            Socket s = new Socket("localhost",6666);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF("searchItem");
            dout.writeUTF(Login.loggedInUser);
            dout.writeUTF("Product Category");
            dout.writeUTF(sort_order);
            dout.writeUTF("null");
            dout.flush();

            ObjectInputStream oin = new ObjectInputStream(s.getInputStream());
            CachedRowSet result =  (CachedRowSet) oin.readObject();
            dout.close();
            s.close();
            
            
                while (result.next()) {
                    int item_id = result.getInt("item_id");
                    String category = result.getString("item_category");
                    String name = result.getString("item_name");
                    int quantity = result.getInt("item_quantity");
                    int price = result.getInt("item_price");
                    int th = result.getInt("threshold");
                    model.addRow(new Object[]{item_id, name, category, quantity, th, price});
                }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_displayActionPerformed

    private void showallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showallActionPerformed
        // TODO add your handling code here:\
        showall();
    }//GEN-LAST:event_showallActionPerformed

    private void display2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_display2ActionPerformed
        // TODO add your handling code here:
//        Connection con = null;
//        ResultSet rs = null;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        String sort_order = start.getText();
        String[] keywords = sort_order.split("\\s+");
        System.out.println(sort_order + "sdfdf");
        try {
            /*
            Class.forName(classForName);
            con = DriverManager.getConnection(getConnection, username, password);
            String query = "SELECT * FROM foodInventory WHERE username=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, Login.loggedInUser);

            System.out.println(pst);
            */
            
            Socket s = new Socket("localhost",6666);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF("populateTable");
            dout.writeUTF(Login.loggedInUser);
            dout.writeUTF("item_id");
            dout.writeUTF("null");
            dout.flush();

            ObjectInputStream oin = new ObjectInputStream(s.getInputStream());
            CachedRowSet result =  (CachedRowSet) oin.readObject();
            dout.close();
            s.close();
            
            
            
            while (result.next()) {
                int item_id = result.getInt("item_id");
                String category = result.getString("item_category");
                String name = result.getString("item_name");
                int quantity = result.getInt("item_quantity");
                int price = result.getInt("item_price");
                int th = result.getInt("threshold");
                int fl = 0;
                String description=result.getString("item_description");
//                           description.toLowerCase();
                System.out.println("des = "+description);
                for(String key:keywords)
                {   
                    boolean res=Pattern.compile(Pattern.quote(key), Pattern.CASE_INSENSITIVE).matcher(description).find();
                    System.out.println("res = "+res);
                    if(!res) 
                    {   
                        // one of the keywords entered by user is not found, so break
                        fl=1;
                        break;
                    }
                        
                }
//                      System.out.println(fl+" "+i);
                if (fl == 0) {
                    model.addRow(new Object[]{item_id, name, category, quantity, th, price});
                }
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_display2ActionPerformed

    private void dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardActionPerformed
        // TODO add your handling code here:
        DashboardFood obj = new DashboardFood();
        obj.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_dashboardActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> categ;
    private javax.swing.JLabel category;
    private javax.swing.JButton dashboard;
    private javax.swing.JButton display;
    private javax.swing.JButton display2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton showall;
    private javax.swing.JTextField start;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
