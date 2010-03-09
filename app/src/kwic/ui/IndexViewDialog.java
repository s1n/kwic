/*
 * IndexViewDialog.java
 *
 * Created on Feb 27, 2010, 6:55:44 PM
 */
package kwic.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import kwic.index.CircularShifter;
import kwic.index.IndexList;
import kwic.index.IndexedString;
import kwic.index.InputReader;
import org.jdesktop.application.Action;
import org.jdesktop.application.Task;

/**
 *
 * @author s1n
 */
public class IndexViewDialog extends javax.swing.JDialog {

   /** Creates new form IndexViewDialog */
   public IndexViewDialog(java.awt.Frame parent, boolean modal) {
      super(parent, modal);
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

      _indexPopupMenu = new javax.swing.JPopupMenu();
      _indexRemovePopup = new javax.swing.JMenuItem();
      _inputPopupMenu = new javax.swing.JPopupMenu();
      _inputRemovePopup = new javax.swing.JMenuItem();
      _continueButton = new javax.swing.JButton();
      _loadButton = new javax.swing.JButton();
      _inputScrollPane = new javax.swing.JScrollPane();
      _inputRecordList = new javax.swing.JTable();
      _indexScrollPane = new javax.swing.JScrollPane();
      _indexRecordList = new javax.swing.JTable();

      _indexPopupMenu.setName("_indexPopupMenu"); // NOI18N

      javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(kwic.ui.Main.class).getContext().getActionMap(IndexViewDialog.class, this);
      _indexRemovePopup.setAction(actionMap.get("removeIndex")); // NOI18N
      org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(kwic.ui.Main.class).getContext().getResourceMap(IndexViewDialog.class);
      _indexRemovePopup.setText(resourceMap.getString("_indexRemovePopup.text")); // NOI18N
      _indexRemovePopup.setName("_indexRemovePopup"); // NOI18N
      _indexPopupMenu.add(_indexRemovePopup);

      _inputPopupMenu.setName("_inputPopupMenu"); // NOI18N

      _inputRemovePopup.setAction(actionMap.get("removeInputAndIndex")); // NOI18N
      _inputRemovePopup.setText(resourceMap.getString("_inputRemovePopup.text")); // NOI18N
      _inputRemovePopup.setName("_inputRemovePopup"); // NOI18N
      _inputPopupMenu.add(_inputRemovePopup);

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      setName("Form"); // NOI18N

      _continueButton.setAction(actionMap.get("continueLoad")); // NOI18N
      _continueButton.setText(resourceMap.getString("_continueButton.text")); // NOI18N
      _continueButton.setName("_continueButton"); // NOI18N

      _loadButton.setAction(actionMap.get("loadFile")); // NOI18N
      _loadButton.setText(resourceMap.getString("_loadButton.text")); // NOI18N
      _loadButton.setName("_loadButton"); // NOI18N

      _inputScrollPane.setName("_inputScrollPane"); // NOI18N

      _inputRecordList.setModel(new javax.swing.table.DefaultTableModel(
         new Object [][] {
            {null, null}
         },
         new String [] {
            "Index", "Shifted Input"
         }
      ) {
         Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class
         };
         boolean[] canEdit = new boolean [] {
            false, false
         };

         public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
         }

         public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
         }
      });
      _inputRecordList.setComponentPopupMenu(_inputPopupMenu);
      _inputRecordList.setName("_inputRecordList"); // NOI18N
      _inputScrollPane.setViewportView(_inputRecordList);

      _indexScrollPane.setName("_indexScrollPane"); // NOI18N

      _indexRecordList.setModel(new javax.swing.table.DefaultTableModel(
         new Object [][] {
            {null, null}
         },
         new String [] {
            "Index", "Shifted Input"
         }
      ) {
         Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class
         };
         boolean[] canEdit = new boolean [] {
            false, false
         };

         public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
         }

         public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
         }
      });
      _indexRecordList.setComponentPopupMenu(_indexPopupMenu);
      _indexRecordList.setName("_indexRecordList"); // NOI18N
      _indexScrollPane.setViewportView(_indexRecordList);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                  .addComponent(_loadButton)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(_continueButton))
               .addComponent(_indexScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
               .addComponent(_inputScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(_inputScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(_indexScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(_continueButton)
               .addComponent(_loadButton))
            .addContainerGap())
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

   /**
    * @param args the command line arguments
    */
   public static void main(String args[]) {
      java.awt.EventQueue.invokeLater(new Runnable() {

         public void run() {
            IndexViewDialog dialog = new IndexViewDialog(new javax.swing.JFrame(), true);
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

   @Action
   public Task loadFile() {
      return new LoadFileTask(org.jdesktop.application.Application.getInstance(kwic.ui.Main.class));
   }

   private class LoadFileTask extends org.jdesktop.application.Task<Object, Void> {

      LoadFileTask(org.jdesktop.application.Application app) {
         super(app);

         //bring up the file dialog to select a file
         JFileChooser fc = new JFileChooser();
         int returnVal = fc.showOpenDialog(IndexViewDialog.this);

         if (returnVal == JFileChooser.APPROVE_OPTION) {
            _file = fc.getSelectedFile();
         } else {
            _file = null;
         }
         dlminput = ((DefaultTableModel) IndexViewDialog.this._inputRecordList.getModel());
         dlmindex = ((DefaultTableModel) IndexViewDialog.this._indexRecordList.getModel());
      }

      @Override
      protected Object doInBackground() {
         InputReader ir = null;
         IndexedString si = null;
         //open the input file for reading
         try {
            //clear out everything to start fresh
            IndexViewDialog.this._index = new IndexList(new CircularShifter());
            ir = new InputReader(_file.toString());
            this.dlminput.setRowCount(0);
            this.dlmindex.setRowCount(0);
         } catch (FileNotFoundException ex) {
            Logger.getLogger(IndexViewDialog.class.getName()).log(Level.SEVERE, null, ex);
         }

         //read in the input, one IndexedString after the next
         do {
            try {
               si = ir.next();
            } catch (java.util.regex.PatternSyntaxException pse) {
               //FIXME could mark bad lines and then show them somewhere
               pse.printStackTrace();
               continue;
            }
            if (si == null) {
               break;
            }
            IndexViewDialog.this._index.add(si);
            this.dlminput.addRow(new Object[]{si.getIndex(), si.toString()});
         } while (true);

         //update the DataModel for the indexed data
         for (IndexedString sin : IndexViewDialog.this._index) {
            System.out.println(sin.toString());
            this.dlmindex.addRow(new Object[]{sin.getIndex(), sin.toString()});
         }
         try {
            ir.close();
         } catch (IOException ex) {
            Logger.getLogger(IndexViewDialog.class.getName()).log(Level.SEVERE, null, ex);
         }

         return IndexViewDialog.this._index;
      }

      @Override
      protected void succeeded(Object result) {
         IndexViewDialog.this._inputRecordList.setModel(dlminput);
         IndexViewDialog.this._indexRecordList.setModel(dlmindex);
      }
      private File _file;
      private DefaultTableModel dlmindex = null;
      private DefaultTableModel dlminput = null;
   }

   @Action
   public void removeIndex() {
      int[] selection = IndexViewDialog.this._indexRecordList.getSelectedRows();
      ;
      DefaultTableModel dlmindex = ((DefaultTableModel) IndexViewDialog.this._indexRecordList.getModel());
      for (int cand = selection.length - 1; cand >= 0; cand--) {
         int idx = selection[cand];
         String[] tokens = {dlmindex.getValueAt(idx, 0).toString(), dlmindex.getValueAt(idx, 1).toString()};
         IndexedString rm = new IndexedString(tokens[1].trim(), tokens[0].trim());

         if (IndexViewDialog.this._index.containsIndex(rm)) {
            IndexViewDialog.this._index.remove(rm);
            dlmindex.removeRow(idx);
         }
         IndexViewDialog.this._indexRecordList.updateUI();
      }
   }

   @Action
   public void removeInputAndIndex() {
      //copies the currently selected input lines
      int[] selection = IndexViewDialog.this._inputRecordList.getSelectedRows();
      DefaultTableModel dlminput = ((DefaultTableModel) IndexViewDialog.this._inputRecordList.getModel());
      DefaultTableModel dlmindex = ((DefaultTableModel) IndexViewDialog.this._indexRecordList.getModel());
      //loop over the selections in input, and remove the shifts from the output
      for (int cand = selection.length - 1; cand >= 0; cand--) {
         int idx = selection[cand];
         String input = dlminput.getValueAt(idx, 1).toString();
         IndexedString rmin = IndexViewDialog.this._index.findFirstInputMatch(input.trim());
         System.out.println("Removing ====> " + input + " / " + rmin.getIndex());
         if (rmin != null && IndexViewDialog.this._index.containsInput(rmin)) {
            //we've identified the inputRecord, let's remove all the indexRecords
            for (int i = 0; i < IndexViewDialog.this._index.size(); i++) {
               String[] tokens = {dlmindex.getValueAt(i, 0).toString(), dlmindex.getValueAt(i, 1).toString()};
               IndexedString rmidx = IndexViewDialog.this._index.findFirstIndexMatch(tokens[0].trim());
               System.out.println("Checking " + rmidx.toString() + " / " + rmidx.getIndex() + " / " + rmidx.originIndex());

               //I think this is the line that prevents a remove if you've removed the indexed lines
               if (rmidx != null && rmin.getIndex().equalsIgnoreCase(rmidx.originIndex())) {
                  //if no more origin index values match rm.getOriginIndex(), remove the input line
                  System.out.println("Removing " + i + " @ " + rmidx.toString());
                  IndexViewDialog.this._index.remove(rmidx);
                  dlmindex.removeRow(i--);
               } else if (rmidx != null && rmin.compareTo(rmidx) == 0) {
                  System.out.println("Removing origin " + i + " @ " + rmidx.toString());
                  IndexViewDialog.this._index.remove(rmidx);
                  dlmindex.removeRow(i--);
               }
            }

            //finally, remove the inputRecord
            IndexViewDialog.this._index.remove(rmin);

            //find where the rmin is in the DataModel
            for (int i = 0; i < dlmindex.getRowCount() - 1; i++) {
               if (dlmindex.getValueAt(i, 0).toString().startsWith(rmin.getIndex())) {
                  dlmindex.removeRow(i--);
                  System.out.println("More Removing " + i + " @ " + rmin.toString());
               }
            }

            //remove it from the input as well
            dlminput.removeRow(idx);
         }
         System.out.println("Index size: " + IndexViewDialog.this._index.size());
         //force a GUI update
         IndexViewDialog.this._inputRecordList.updateUI();
      }
   }

   public IndexList getIndex() {
      return this._index;
   }

   @Action
   public void continueLoad() {
      this.setVisible(false);
   }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton _continueButton;
   private javax.swing.JPopupMenu _indexPopupMenu;
   private javax.swing.JTable _indexRecordList;
   private javax.swing.JMenuItem _indexRemovePopup;
   private javax.swing.JScrollPane _indexScrollPane;
   private javax.swing.JPopupMenu _inputPopupMenu;
   private javax.swing.JTable _inputRecordList;
   private javax.swing.JMenuItem _inputRemovePopup;
   private javax.swing.JScrollPane _inputScrollPane;
   private javax.swing.JButton _loadButton;
   // End of variables declaration//GEN-END:variables
   private IndexList _index = null;
}
