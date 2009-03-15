/*
 * Copyright 2008 Ayman Al-Sairafi ayman.alsairafi@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License
 *       at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jsyntaxpane.actions.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.ref.WeakReference;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.swing.JCheckBox;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import jsyntaxpane.actions.ActionUtils;
import jsyntaxpane.actions.DocumentSearchData;
import jsyntaxpane.components.Markers;
import jsyntaxpane.components.Markers.SimpleMarker;
import jsyntaxpane.util.SwingUtils;

/**
 * QuickFind Dialog.  Firefox like dialog shown at bottom of editor.
 * @author Ayman Al-Sairafi
 */
public class QuickFindDialog extends javax.swing.JDialog implements DocumentListener, ActionListener {

	private SimpleMarker marker = new SimpleMarker(Color.PINK);
	private WeakReference<JTextComponent> target;
	private WeakReference<DocumentSearchData> dsd;
	private int oldCaretPosition;

	/**
	 * Creates new form QuickFindDialog
	 *
	 * @param target
	 * @param data search data
	 */
	public QuickFindDialog(final JTextComponent target, DocumentSearchData data) {
		super(ActionUtils.getFrameFor(target), false);
		initComponents();
		SwingUtils.addEscapeListener(this);
		dsd = new WeakReference<DocumentSearchData>(data);
	}

	public void showFor(final JTextComponent target) {
		oldCaretPosition = target.getCaretPosition();
		JViewport view = (JViewport) target.getParent();
		Dimension wd = getSize();
		wd.width = target.getVisibleRect().width;
		Point loc = new Point(0, view.getHeight());
		setSize(wd);
		setLocationRelativeTo(view);
		SwingUtilities.convertPointToScreen(loc, view);
		setLocation(loc);
		jTxtFind.setFont(target.getFont());
		jTxtFind.getDocument().addDocumentListener(this);
		WindowAdapter closeListener = new WindowAdapter() {

			@Override
			public void windowDeactivated(WindowEvent e) {
				target.getDocument().removeDocumentListener(QuickFindDialog.this);
				Markers.removeMarkers(target, marker);
				target.setCaretPosition(oldCaretPosition);
				dispose();
			}
		};
		addWindowListener(closeListener);
		this.target = new WeakReference<JTextComponent>(target);
		Pattern p = dsd.get().getPattern();
		if (p != null) {
			jTxtFind.setText(p.pattern());
		}
		jChkWrap.setSelected(dsd.get().isWrap());
		setVisible(true);
	}

	/**
	 * This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jLabel1 = new javax.swing.JLabel();
        jTxtFind = new javax.swing.JTextField();
        jBtnPrev = new javax.swing.JButton();
        jBtnNext = new javax.swing.JButton();
        jChkIgnoreCase = new javax.swing.JCheckBox();
        jChkRegExp = new javax.swing.JCheckBox();
        jChkWrap = new javax.swing.JCheckBox();
        jLblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(java.awt.Color.darkGray);
        setName("QuickFindDialog"); // NOI18N
        setResizable(false);
        setUndecorated(true);

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.add(jSeparator1);

        jLabel1.setLabelFor(jTxtFind);
        jLabel1.setText("Quick Find");
        jToolBar1.add(jLabel1);

        jTxtFind.setColumns(30);
        jTxtFind.setMaximumSize(new java.awt.Dimension(200, 20));
        jTxtFind.setPreferredSize(new java.awt.Dimension(150, 20));
        jToolBar1.add(jTxtFind);

        jBtnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/META-INF/images/small-icons/go-up.png"))); // NOI18N
        jBtnPrev.setFocusable(false);
        jBtnPrev.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBtnPrev.setOpaque(false);
        jBtnPrev.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBtnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPrevActionPerformed(evt);
            }
        });
        jToolBar1.add(jBtnPrev);

        jBtnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/META-INF/images/small-icons/go-down.png"))); // NOI18N
        jBtnNext.setFocusable(false);
        jBtnNext.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBtnNext.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jBtnNext.setOpaque(false);
        jBtnNext.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBtnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNextActionPerformed(evt);
            }
        });
        jToolBar1.add(jBtnNext);

        jChkIgnoreCase.setMnemonic('C');
        jChkIgnoreCase.setText("Ignore Case");
        jChkIgnoreCase.setFocusable(false);
        jChkIgnoreCase.setOpaque(false);
        jChkIgnoreCase.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jChkIgnoreCase);
        jChkIgnoreCase.addActionListener(this);

        jChkRegExp.setMnemonic('R');
        jChkRegExp.setText("Reg Exp");
        jChkRegExp.setFocusable(false);
        jChkRegExp.setOpaque(false);
        jChkRegExp.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jChkRegExp);
        jChkRegExp.addActionListener(this);

        jChkWrap.setMnemonic('W');
        jChkWrap.setText("Wrap");
        jChkWrap.setFocusable(false);
        jChkWrap.setOpaque(false);
        jChkWrap.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jChkWrap);
        jChkWrap.addActionListener(this);

        jLblStatus.setFont(jLblStatus.getFont().deriveFont(jLblStatus.getFont().getSize()-2f));
        jLblStatus.setForeground(java.awt.Color.red);
        jToolBar1.add(jLblStatus);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void jBtnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNextActionPerformed
		if(dsd.get().doFindNext(target.get())) {
			jLblStatus.setText(null);
		} else {
			jLblStatus.setText("not found");
		}
}//GEN-LAST:event_jBtnNextActionPerformed

	private void jBtnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPrevActionPerformed
		if(dsd.get().doFindPrev(target.get())) {
			jLblStatus.setText(null);
		} else {
			jLblStatus.setText("not found");
		}
}//GEN-LAST:event_jBtnPrevActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnNext;
    private javax.swing.JButton jBtnPrev;
    private javax.swing.JCheckBox jChkIgnoreCase;
    private javax.swing.JCheckBox jChkRegExp;
    private javax.swing.JCheckBox jChkWrap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLblStatus;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField jTxtFind;
    // End of variables declaration//GEN-END:variables

	@Override
	public void insertUpdate(DocumentEvent e) {
		updateFind();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		updateFind();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		updateFind();
	}

	private void updateFind() {
		JTextComponent t = target.get();
		DocumentSearchData d = dsd.get();
		String toFind = jTxtFind.getText();
		if (toFind == null || toFind.isEmpty()) {
			jLblStatus.setText(null);
			return;
		}
		try {
			d.setWrap(jChkWrap.isSelected());
			d.setPattern(toFind,
				jChkRegExp.isSelected(),
				jChkIgnoreCase.isSelected());
			// The dsd doFindNext will always find from current pos,
			// so we need to relocate to our saved pos before we call doFindNext
			jLblStatus.setText(null);
			t.setCaretPosition(oldCaretPosition);
			if (!d.doFindNext(t)) {
				jLblStatus.setText("Not found");
			} else {
				jLblStatus.setText(null);
			}
		} catch (PatternSyntaxException e) {
			jLblStatus.setText(e.getDescription());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JCheckBox) {
			updateFind();
		}
	}
}
