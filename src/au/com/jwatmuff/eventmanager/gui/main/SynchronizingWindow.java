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

package au.com.jwatmuff.eventmanager.gui.main;

import au.com.jwatmuff.eventmanager.util.EventBus;
import java.awt.Color;
import javax.swing.border.LineBorder;

/**
 *
 * @author  James
 */
public class SynchronizingWindow extends javax.swing.JFrame {
    private EventBus.Listener listener;
    
    /** Creates new form SynchronizingWindow */
    public SynchronizingWindow() {
        initComponents();
        // center window on screen
        setLocationRelativeTo(null);
        getRootPane().setBorder(new LineBorder(Color.BLACK, 1));
        
        listener = new EventBus.Listener() {
            @Override
            public void receive(String key, Object value) {
                if(key.equals("sync-status")) {
                    statusLabel.setText((String)value);
                }
            }
        };
        EventBus.addListener(listener);
    }

    @Override
    public void dispose() {
        EventBus.removeListener(listener);
        super.dispose();
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Synchronizing");
        setAlwaysOnTop(true);
        setLocationByPlatform(true);
        setUndecorated(true);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/famfamfam/icons/silk/arrow_refresh_small.png"))); // NOI18N
        jLabel1.setText("Synchronizing with other computers on the network. Please wait...");

        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables

}
