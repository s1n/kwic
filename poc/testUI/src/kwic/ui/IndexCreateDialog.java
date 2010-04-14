/*
 * IndexCreateDialog.java
 *
 * Created on Mar 8, 2010, 7:38:12 PM
 */

package kwic.ui;

import kwic.index.CircularShifter;
import kwic.index.IndexList;
import kwic.index.IndexedString;
import kwic.index.InputReader;
import org.jdesktop.application.Action;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;

/**
 *
 * @author cliff
 */
public class IndexCreateDialog extends javax.swing.JDialog {

    /** Creates new form IndexCreateDialog */
    public IndexCreateDialog(java.awt.Frame parent, boolean modal) {
      super(parent, modal);
      initComponents();
   }

   @Action
   public void importText() {

       String input = _inputList.getText();
       IndexedString si = null;
       IndexCreateDialog.this._index = new IndexList(new CircularShifter());
       try{
           for (int i=0; i < _inputList.getLineCount(); i++) {
            int start = _inputList.getLineStartOffset(i);
            int end   = _inputList.getLineEndOffset(i);
            String line = input.substring(start, end);
            if (line != null) {
                si = new IndexedString(line);
                IndexCreateDialog.this._index.add(si);
             }
            }
       }
       catch (BadLocationException ex) {
         Logger.getLogger(InputReader.class.getName()).log(Level.SEVERE, null, ex);
         ex.printStackTrace();
      }

       this.setVisible(false);
    }

   @Action
   public void cancelText() {
       _index = null;
      this.setVisible(false);
   }

    public IndexList getIndex() {
      return this._index;
   }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      _okButton = new javax.swing.JButton();
      _cancelButton = new javax.swing.JButton();
      jScrollPane1 = new javax.swing.JScrollPane();
      _inputList = new javax.swing.JTextArea();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      setName("Form"); // NOI18N

      javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(kwic.ui.Main.class).getContext().getActionMap(IndexCreateDialog.class, this);
      _okButton.setAction(actionMap.get("importText")); // NOI18N
      org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(kwic.ui.Main.class).getContext().getResourceMap(IndexCreateDialog.class);
      _okButton.setText(resourceMap.getString("_okButton.text")); // NOI18N
      _okButton.setName("_okButton"); // NOI18N

      _cancelButton.setAction(actionMap.get("cancelText")); // NOI18N
      _cancelButton.setText(resourceMap.getString("_cancelButton.text")); // NOI18N
      _cancelButton.setName("_cancelButton"); // NOI18N

      jScrollPane1.setName("jScrollPane1"); // NOI18N

      _inputList.setColumns(20);
      _inputList.setRows(5);
      _inputList.setName("_inputList"); // NOI18N
      jScrollPane1.setViewportView(_inputList);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                  .addComponent(_okButton)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addComponent(_cancelButton))
               .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(_cancelButton)
               .addComponent(_okButton))
            .addContainerGap())
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
      java.awt.EventQueue.invokeLater(new Runnable() {

         public void run() {
            IndexCreateDialog dialog = new IndexCreateDialog(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {

               @Override
               public void windowClosing(java.awt.event.WindowEvent e) {
                  System.exit(0);
               }
            });
            dialog.setVisible(true);
         }
      });
   }


   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton _cancelButton;
   private javax.swing.JTextArea _inputList;
   private javax.swing.JButton _okButton;
   private javax.swing.JScrollPane jScrollPane1;
   // End of variables declaration//GEN-END:variables

    private IndexList _index = null;
}
