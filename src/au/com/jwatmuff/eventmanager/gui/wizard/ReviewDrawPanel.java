/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReviewDrawPanel.java
 *
 * Created on 22/09/2010, 10:11:13 PM
 */

package au.com.jwatmuff.eventmanager.gui.wizard;

import au.com.jwatmuff.eventmanager.gui.draw.FightTableModel;
import au.com.jwatmuff.eventmanager.gui.draw.PlayerTableModel;
import au.com.jwatmuff.eventmanager.gui.wizard.DrawWizardWindow.Context;
import au.com.jwatmuff.eventmanager.model.misc.DatabaseStateException;
import au.com.jwatmuff.eventmanager.model.misc.PoolLocker;
import au.com.jwatmuff.eventmanager.model.vo.Pool;
import au.com.jwatmuff.eventmanager.permissions.Action;
import au.com.jwatmuff.eventmanager.permissions.PermissionChecker;
import au.com.jwatmuff.eventmanager.print.MultipleDrawHTMLGenerator;
import au.com.jwatmuff.eventmanager.util.GUIUtils;
import au.com.jwatmuff.genericdb.transaction.TransactionNotifier;
import au.com.jwatmuff.genericdb.transaction.TransactionalDatabase;
import java.util.ArrayList;

/**
 *
 * @author James
 */
public class ReviewDrawPanel extends javax.swing.JPanel implements DrawWizardWindow.Panel {
    private TransactionalDatabase database;
    private TransactionNotifier notifier;
    private Context context;

    private PlayerTableModel playerTableModel;
    private FightTableModel fightTableModel;

    /** Creates new form ReviewDrawPanel */
    public ReviewDrawPanel(TransactionalDatabase database, TransactionNotifier notifier, final Context context) {
        this.database = database;
        this.notifier = notifier;
        this.context = context;

        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        playerTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        fightTable = new javax.swing.JTable();
        divisionNameLabel = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        printButton = new javax.swing.JButton();

        playerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Player"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        playerTable.setGridColor(new java.awt.Color(204, 204, 204));
        playerTable.setRowHeight(19);
        jScrollPane1.setViewportView(playerTable);

        fightTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Fight"
            }
        ));
        fightTable.setGridColor(new java.awt.Color(204, 204, 204));
        fightTable.setRowHeight(19);
        jScrollPane2.setViewportView(fightTable);

        divisionNameLabel.setFont(new java.awt.Font("Tahoma", 1, 24));
        divisionNameLabel.setText("Division Name");

        jToolBar1.setFloatable(false);

        printButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/famfamfam/icons/silk/printer.png"))); // NOI18N
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(printButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(divisionNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(530, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(divisionNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(317, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        ArrayList<Pool> pools = new ArrayList<>();
        pools.add(context.pool);
        new MultipleDrawHTMLGenerator(database, pools, false).openInBrowser();
}//GEN-LAST:event_printButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel divisionNameLabel;
    private javax.swing.JTable fightTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable playerTable;
    private javax.swing.JButton printButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean nextButtonPressed() {
        if(!GUIUtils.confirmLock(null, context.pool.getDescription())) return false;
        if(!PermissionChecker.isAllowed(Action.LOCK_DRAW, database)) return false;
        try {
            PoolLocker.lockPoolFights(database, context.pool);
            return true;
        } catch(DatabaseStateException e) {
            GUIUtils.displayError(null, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean backButtonPressed() {
        return true;
    }

    @Override
    public boolean closedButtonPressed() {
        return true;
    }

    @Override
    public void beforeShow() {
        divisionNameLabel.setText(context.pool.getDescription() + ": Review Draw");

        playerTableModel = new PlayerTableModel(database, notifier) {
            @Override
            public Pool getPool() {
                return context.pool;
            }
        };
        fightTableModel = new FightTableModel(database, notifier) {
            @Override
            public Pool getPool() {
                return context.pool;
            }
        };
        playerTableModel.updateFromDatabase();
        fightTableModel.updateFromDatabase();

        playerTable.setModel(playerTableModel);
        fightTable.setModel(fightTableModel);

        GUIUtils.leftAlignTable(fightTable);
    }

    @Override
    public void afterHide() {
        playerTableModel.shutdown();
        fightTableModel.shutdown();
    }
}
