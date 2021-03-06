/*
 * EventManager
 * Copyright (c) 2008-2017 James Watmuff & Leonard Hall
 *
 * This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package au.com.jwatmuff.eventmanager.gui.results;

import au.com.jwatmuff.eventmanager.db.PoolDAO;
import au.com.jwatmuff.eventmanager.db.ResultDAO;
import au.com.jwatmuff.eventmanager.gui.main.Icons;
import au.com.jwatmuff.eventmanager.model.cache.DivisionResultCache;
import au.com.jwatmuff.eventmanager.model.cache.ResultInfoCache;
import au.com.jwatmuff.eventmanager.model.info.ResultInfo;
import au.com.jwatmuff.eventmanager.model.vo.Pool;
import au.com.jwatmuff.eventmanager.model.vo.Result;
import au.com.jwatmuff.eventmanager.print.DivisionResultHTMLGenerator;
import au.com.jwatmuff.eventmanager.print.GradingPointsHTMLGenerator;
import au.com.jwatmuff.eventmanager.print.MultipleDrawHTMLGenerator;
import au.com.jwatmuff.eventmanager.print.PlayerListHTMLGenerator;
import au.com.jwatmuff.eventmanager.print.ResultListHTMLGenerator;
import au.com.jwatmuff.eventmanager.util.GUIUtils;
import au.com.jwatmuff.eventmanager.util.gui.CheckboxListDialog;
import au.com.jwatmuff.eventmanager.util.gui.StringRenderer;
import au.com.jwatmuff.genericdb.Database;
import au.com.jwatmuff.genericdb.transaction.TransactionNotifier;
import au.com.jwatmuff.genericdb.transaction.TransactionalDatabase;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

/**
 *
 * @author  James
 */
public class PrintPanel extends javax.swing.JPanel {
    public static final Logger log = Logger.getLogger(PrintPanel.class);
    
    private TransactionalDatabase database;
    private TransactionNotifier notifier;
    private Frame parentWindow;
    private ResultInfoCache resultInfoCache;
    private DivisionResultCache divisionResultCache;
    
    public static final Comparator<Pool> POOL_COMPARATOR = new Comparator<Pool>() {
        @Override
        public int compare(Pool p1, Pool p2) {
            if(p1.getMaximumAge() == p2.getMaximumAge()){
                if(p2.getGender().equals(p1.getGender())){
                    if(p1.getMaximumWeight() == p2.getMaximumWeight()){
                        if(p1.getMinimumWeight() == p2.getMinimumWeight()){
                            return  p1.getDescription().compareTo(p2.getDescription());
                        } else {
                            if(p1.getMinimumWeight() == 0) {
                                return 1;
                            } else if(p2.getMinimumWeight() == 0) {
                                return -1;
                            } else {
                                return -Double.compare(p1.getMinimumWeight(), p2.getMinimumWeight());
                            }
                        }
                    } else {
                        if(p1.getMaximumWeight() == 0) {
                            return 1;
                        } else if(p2.getMaximumWeight() == 0) {
                            return -1;
                        } else {
                            return Double.compare(p1.getMaximumWeight(), p2.getMaximumWeight());
                        }
                    }
                } else {
                    return  p2.getGender().compareTo(p1.getGender());
                }
            } else {
                if(p1.getMaximumAge() == 0) {
                    return 1;
                } else if(p2.getMaximumAge() == 0) {
                    return -1;
                } else {
                    return p1.getMaximumAge() - p2.getMaximumAge();
                }
            }
        }
    };

    

    /** Creates new form FightProgressionPanel */
    public PrintPanel() {
        initComponents();
    }
    
    @Required
    public void setDatabase(TransactionalDatabase database) {
        this.database = database;
    }
    
    @Required
    public void setNotifier(TransactionNotifier notifier) {
        this.notifier = notifier;
    }
    
    public void setParentWindow(Frame parentWindow) {
        this.parentWindow = parentWindow;
    }
    
    public void afterPropertiesSet() {
        resultInfoCache = new ResultInfoCache(database, notifier);
        divisionResultCache = new DivisionResultCache(database, notifier);
    }

    public void shutdown() {
        resultInfoCache.shutdown();
        divisionResultCache.shutdown();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        resultsSummaryButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pointsButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        divisionResultsButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        drawSheetsButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        playerListButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        resultsSummaryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/famfamfam/icons/silk/printer.png"))); // NOI18N
        resultsSummaryButton.setText("Results Summary..");
        resultsSummaryButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        resultsSummaryButton.setIconTextGap(8);
        resultsSummaryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultsSummaryButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Print all fight results recorded in the competition");

        pointsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/famfamfam/icons/silk/printer.png"))); // NOI18N
        pointsButton.setText("Points Cards..");
        pointsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        pointsButton.setIconTextGap(8);
        pointsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pointsButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Print points cards");

        divisionResultsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/famfamfam/icons/silk/printer.png"))); // NOI18N
        divisionResultsButton.setText("Division Results..");
        divisionResultsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        divisionResultsButton.setIconTextGap(8);
        divisionResultsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                divisionResultsButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Print first, second and third positions for each division");

        drawSheetsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/famfamfam/icons/silk/printer.png"))); // NOI18N
        drawSheetsButton.setText("Draw Sheets..");
        drawSheetsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        drawSheetsButton.setIconTextGap(8);
        drawSheetsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawSheetsButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Print draw sheets showing results");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Print Results");

        playerListButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/famfamfam/icons/silk/printer.png"))); // NOI18N
        playerListButton.setText("Player List...");
        playerListButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        playerListButton.setIconTextGap(8);
        playerListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerListButtonActionPerformed(evt);
            }
        });

        jLabel6.setText("Print a list of all players in the competition");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator10)
                    .addComponent(jLabel5)
                    .addComponent(playerListButton)
                    .addComponent(jLabel6)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3)
                    .addComponent(resultsSummaryButton)
                    .addComponent(jLabel1)
                    .addComponent(pointsButton)
                    .addComponent(jLabel2)
                    .addComponent(divisionResultsButton)
                    .addComponent(jLabel3)
                    .addComponent(drawSheetsButton)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {divisionResultsButton, drawSheetsButton, playerListButton, pointsButton, resultsSummaryButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerListButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultsSummaryButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pointsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(divisionResultsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(drawSheetsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(52, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void resultsSummaryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultsSummaryButtonActionPerformed
    ResultInfoMapper mapper = new ResultInfoMapper(database);
    List<Map> results = new ArrayList<>();
    for(Result r : database.findAll(Result.class, ResultDAO.ALL)) {
        try {
            ResultInfo ri = resultInfoCache.getResultInfo(r.getID());
            results.add(mapper.mapBean(ri));
        } catch(Exception e) {
            log.info(e,e);
        }
    }
    
    // sort by fight number, then time recorded
    Collections.sort(results, new Comparator<Map>() {
        @Override
        @SuppressWarnings("unchecked")
        public int compare(Map o1, Map o2) {
            Comparable f1 = (Comparable) o1.get("matfight");
            Comparable f2 = (Comparable) o2.get("matfight");
            Comparable t1 = (Comparable) o1.get("timerec");
            Comparable t2 = (Comparable) o2.get("timerec");
            int f = f1.compareTo(f2);
            return f == 0 ? t1.compareTo(t2) : f;
        }
    });
    
    new ResultListHTMLGenerator(database, results).openInBrowser();
}//GEN-LAST:event_resultsSummaryButtonActionPerformed

private void pointsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pointsButtonActionPerformed
    new GradingPointsHTMLGenerator(database, resultInfoCache).openInBrowser();
}//GEN-LAST:event_pointsButtonActionPerformed

private void divisionResultsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_divisionResultsButtonActionPerformed
    List<Pool> divisions = new ArrayList<>();
    divisions.addAll(database.findAll(Pool.class, PoolDAO.WITH_LOCKED_STATUS, Pool.LockedStatus.FIGHTS_LOCKED));
    divisionResultCache.filterDivisionsWithoutResults(divisions);
    Collections.sort(divisions, POOL_COMPARATOR);
    CheckboxListDialog<Pool> dialog = new CheckboxListDialog<>(null, true, divisions, "Select divisions to print", "Print Division Results");
    dialog.setRenderer(new StringRenderer<Pool>() {
        @Override
            public String asString(Pool p) { return p.getDescription(); }
        }, Icons.POOL);
    dialog.setVisible(true);
    if(dialog.getSuccess()) {
        new DivisionResultHTMLGenerator(database, divisionResultCache, dialog.getSelectedItems()).openInBrowser();
    }
}//GEN-LAST:event_divisionResultsButtonActionPerformed

private void drawSheetsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawSheetsButtonActionPerformed
    List<Pool> pools = new ArrayList<>();
    pools.addAll(database.findAll(Pool.class, PoolDAO.WITH_LOCKED_STATUS, Pool.LockedStatus.FIGHTS_LOCKED));
    Collections.sort(pools, POOL_COMPARATOR);
    if(pools.isEmpty()) {
        GUIUtils.displayMessage(null, "At least one division with locked fights must exist to print results", "Print Results");
        return;
    }
    //ListDialog<Pool> dialog = new ListDialog<Pool>(parentWindow, true, pools, "Choose Division", "Print Results");
    CheckboxListDialog<Pool> dialog = new CheckboxListDialog<>(parentWindow, true, pools, "Choose Division", "Print Results");
    dialog.setRenderer(new StringRenderer<Pool>() {
            @Override
            public String asString(Pool p) {
                return p.getDescription();
            }
    }, Icons.POOL);
    dialog.setVisible(true);
    if(!dialog.getSuccess()) return;

//    for(Pool pool : dialog.getSelectedItems()) {
        //int poolID = dialog.getSelectedItem().getID();

  //      new DrawHTMLGenerator(database, pool.getID(), true).openInBrowser();
    //}
    new MultipleDrawHTMLGenerator(database, dialog.getSelectedItems(), true).openInBrowser();
}//GEN-LAST:event_drawSheetsButtonActionPerformed

    private void playerListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerListButtonActionPerformed
        new PlayerListHTMLGenerator(database).openInBrowser();
    }//GEN-LAST:event_playerListButtonActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton divisionResultsButton;
    private javax.swing.JButton drawSheetsButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JButton playerListButton;
    private javax.swing.JButton pointsButton;
    private javax.swing.JButton resultsSummaryButton;
    // End of variables declaration//GEN-END:variables

}
