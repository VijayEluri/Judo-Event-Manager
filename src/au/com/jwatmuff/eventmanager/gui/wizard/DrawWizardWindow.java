/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DrawWizardWindow.java
 *
 * Created on 24/08/2010, 10:31:39 PM
 */

package au.com.jwatmuff.eventmanager.gui.wizard;

import au.com.jwatmuff.eventmanager.model.vo.Pool;
import au.com.jwatmuff.genericdb.transaction.TransactionNotifier;
import au.com.jwatmuff.genericdb.transaction.TransactionalDatabase;
import java.awt.CardLayout;
import java.awt.Component;
import org.apache.log4j.Logger;

/**
 *
 * @author James
 */
public class DrawWizardWindow extends javax.swing.JFrame {
    private static final Logger log = Logger.getLogger(DrawWizardWindow.class);

    public class Context {
        public Pool pool;
    }

    public interface Panel {
        /*
         * All panels of the wizard must implement this interface
         *
         * If a panel returns false from any of these methods, it tells the
         * wizard window not to perform any action in response to the button
         * press
         */
        boolean nextButtonPressed();
        boolean backButtonPressed();
        boolean closedButtonPressed();
        void beforeShow();
        void afterHide();
    }

    private CardLayout layout = new CardLayout();
    final private Panel[] panels;
    private int currentIndex;
    private Context context = new Context();

    /** Creates new form DrawWizardWindow */
    public DrawWizardWindow(TransactionalDatabase database, TransactionNotifier notifier, Pool pool) {
        context.pool = pool;

        panels = new Panel[] {
            new PlayerSelectionPanel(database, notifier, context),
            new SeedingPanel(database, notifier, context)
        };

        initComponents();

        currentIndex = 0;
        panels[0].beforeShow();

        contentPanel.setLayout(layout);
        int index = 0;
        for(Panel panel : panels) {
            contentPanel.add((Component) panel, String.valueOf(index++));
        }

        updateButtons();

        setLocationRelativeTo(null);
        pack();
    }

    private Panel getCurrentPanel() {
        return panels[currentIndex];
    }

    private void next() {
        currentIndex++;
        if(currentIndex < panels.length) {
            panels[currentIndex].beforeShow();
            layout.next(contentPanel);
            panels[currentIndex - 1].afterHide();
        } else {
            log.warn("Next tried to go past end of available panels");
        }
        updateButtons();
    }

    private void previous() {
        currentIndex--;
        if(currentIndex >= 0) {
            panels[currentIndex].beforeShow();
            layout.previous(contentPanel);
            panels[currentIndex + 1].afterHide();
        } else {
            log.warn("Previous tried to go past start of available panels");
        }
        updateButtons();
    }

    private void updateButtons() {
        nextButton.setEnabled(currentIndex < panels.length - 1);
        // hack to disable back button on SeedingPanel
        backButton.setEnabled(currentIndex > 0 && !(getCurrentPanel() instanceof SeedingPanel));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        contentPanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        closeButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Draw Wizard");

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 501, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 319, Short.MAX_VALUE)
        );

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/famfamfam/icons/silk/resultset_previous.png"))); // NOI18N
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        nextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/famfamfam/icons/silk/resultset_next.png"))); // NOI18N
        nextButton.setText("Next");
        nextButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(closeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextButton))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {backButton, closeButton, nextButton});

        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextButton)
                    .addComponent(backButton)
                    .addComponent(closeButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        if(getCurrentPanel().nextButtonPressed())
            next();
}//GEN-LAST:event_nextButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        if(getCurrentPanel().closedButtonPressed())
            this.setVisible(false);
    }//GEN-LAST:event_closeButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        if(getCurrentPanel().backButtonPressed())
            previous();
    }//GEN-LAST:event_backButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton nextButton;
    // End of variables declaration//GEN-END:variables

}
