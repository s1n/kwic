/*
 * uiView.java
 */
package kwic.ui;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import kwic.index.IndexList;
import kwic.index.IndexedString;

/**
 * The application's main frame.
 */
public class MainWindow extends FrameView {

   public MainWindow(SingleFrameApplication app) {
      super(app);

      initComponents();

      // status bar initialization - message timeout, idle icon and busy animation, etc
      ResourceMap resourceMap = getResourceMap();
      int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
      messageTimer = new Timer(messageTimeout, new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            _statusMessageLabel.setText("");
         }
      });
      messageTimer.setRepeats(false);
      int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
      for (int i = 0; i < busyIcons.length; i++) {
         busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
      }
      busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
            _statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
         }
      });
      idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
      _statusAnimationLabel.setIcon(idleIcon);
      _progressBar.setVisible(false);

      // connecting action tasks to status bar via TaskMonitor
      TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
      taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

         public void propertyChange(java.beans.PropertyChangeEvent evt) {
            String propertyName = evt.getPropertyName();
            if ("started".equals(propertyName)) {
               if (!busyIconTimer.isRunning()) {
                  _statusAnimationLabel.setIcon(busyIcons[0]);
                  busyIconIndex = 0;
                  busyIconTimer.start();
               }
               _progressBar.setVisible(true);
               _progressBar.setIndeterminate(true);
            } else if ("done".equals(propertyName)) {
               busyIconTimer.stop();
               _statusAnimationLabel.setIcon(idleIcon);
               _progressBar.setVisible(false);
               _progressBar.setValue(0);
            } else if ("message".equals(propertyName)) {
               String text = (String) (evt.getNewValue());
               _statusMessageLabel.setText((text == null) ? "" : text);
               messageTimer.restart();
            } else if ("progress".equals(propertyName)) {
               int value = (Integer) (evt.getNewValue());
               _progressBar.setVisible(true);
               _progressBar.setIndeterminate(false);
               _progressBar.setValue(value);
            }
         }
      });
   }

   @Action
   public void showAboutBox() {
      if (aboutBox == null) {
         JFrame mainFrame = Main.getApplication().getMainFrame();
         aboutBox = new AboutBox(mainFrame);
         aboutBox.setLocationRelativeTo(mainFrame);
      }
      Main.getApplication().show(aboutBox);
   }

   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        _mainPanel = new javax.swing.JPanel();
        _searchFor = new javax.swing.JTextField();
        _searchButton = new javax.swing.JButton();
        _searchResultsPane = new javax.swing.JScrollPane();
        _searchResults = new javax.swing.JTable();
        _menu = new javax.swing.JMenuBar();
        javax.swing.JMenu _fileMenu = new javax.swing.JMenu();
        _createMenuItem = new javax.swing.JMenuItem();
        _loadMenuItem = new javax.swing.JMenuItem();
        _saveMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem _exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu _helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem _aboutMenuItem = new javax.swing.JMenuItem();
        _statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        _statusMessageLabel = new javax.swing.JLabel();
        _statusAnimationLabel = new javax.swing.JLabel();
        _progressBar = new javax.swing.JProgressBar();

        _mainPanel.setMinimumSize(new java.awt.Dimension(100, 110));
        _mainPanel.setName("_mainPanel"); // NOI18N
        _mainPanel.setPreferredSize(new java.awt.Dimension(939, 500));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(kwic.ui.Main.class).getContext().getResourceMap(MainWindow.class);
        _searchFor.setText(resourceMap.getString("_searchFor.text")); // NOI18N
        _searchFor.setName("_searchFor"); // NOI18N
        _searchFor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                _searchForKeyPressed(evt);
            }
        });

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(kwic.ui.Main.class).getContext().getActionMap(MainWindow.class, this);
        _searchButton.setAction(actionMap.get("searchIndex")); // NOI18N
        _searchButton.setText(resourceMap.getString("_searchButton.text")); // NOI18N
        _searchButton.setName("_searchButton"); // NOI18N

        _searchResultsPane.setName("_searchResultsPane"); // NOI18N

        _searchResults.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Index", "Description", "URL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        _searchResults.setName("_searchResults"); // NOI18N
        _searchResultsPane.setViewportView(_searchResults);

        javax.swing.GroupLayout _mainPanelLayout = new javax.swing.GroupLayout(_mainPanel);
        _mainPanel.setLayout(_mainPanelLayout);
        _mainPanelLayout.setHorizontalGroup(
            _mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(_mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_searchResultsPane, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                    .addGroup(_mainPanelLayout.createSequentialGroup()
                        .addComponent(_searchFor, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_searchButton)))
                .addContainerGap())
        );
        _mainPanelLayout.setVerticalGroup(
            _mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(_mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(_searchFor, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(_searchResultsPane, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addContainerGap())
        );

        _menu.setName("_menu"); // NOI18N

        _fileMenu.setText(resourceMap.getString("_fileMenu.text")); // NOI18N
        _fileMenu.setName("_fileMenu"); // NOI18N

        _createMenuItem.setAction(actionMap.get("createFile")); // NOI18N
        _createMenuItem.setText(resourceMap.getString("_createMenuItem.text")); // NOI18N
        _createMenuItem.setToolTipText(resourceMap.getString("_createMenuItem.toolTipText")); // NOI18N
        _createMenuItem.setName("_createMenuItem"); // NOI18N
        _fileMenu.add(_createMenuItem);

        _loadMenuItem.setAction(actionMap.get("loadFile")); // NOI18N
        _loadMenuItem.setText(resourceMap.getString("_loadMenuItem.text")); // NOI18N
        _loadMenuItem.setToolTipText(resourceMap.getString("_loadMenuItem.toolTipText")); // NOI18N
        _loadMenuItem.setName("_loadMenuItem"); // NOI18N
        _fileMenu.add(_loadMenuItem);

        _saveMenuItem.setAction(actionMap.get("saveIndex")); // NOI18N
        _saveMenuItem.setText(resourceMap.getString("_saveMenuItem.text")); // NOI18N
        _saveMenuItem.setToolTipText(resourceMap.getString("_saveMenuItem.toolTipText")); // NOI18N
        _saveMenuItem.setName("_saveMenuItem"); // NOI18N
        _fileMenu.add(_saveMenuItem);

        jSeparator1.setName("jSeparator1"); // NOI18N
        _fileMenu.add(jSeparator1);

        _exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        _exitMenuItem.setName("_exitMenuItem"); // NOI18N
        _fileMenu.add(_exitMenuItem);

        _menu.add(_fileMenu);

        _helpMenu.setText(resourceMap.getString("_helpMenu.text")); // NOI18N
        _helpMenu.setName("_helpMenu"); // NOI18N

        _aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        _aboutMenuItem.setName("_aboutMenuItem"); // NOI18N
        _helpMenu.add(_aboutMenuItem);

        _menu.add(_helpMenu);

        _statusPanel.setName("_statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        _statusMessageLabel.setName("_statusMessageLabel"); // NOI18N

        _statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        _statusAnimationLabel.setName("_statusAnimationLabel"); // NOI18N

        _progressBar.setName("_progressBar"); // NOI18N

        javax.swing.GroupLayout _statusPanelLayout = new javax.swing.GroupLayout(_statusPanel);
        _statusPanel.setLayout(_statusPanelLayout);
        _statusPanelLayout.setHorizontalGroup(
            _statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
            .addGroup(_statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(_statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 384, Short.MAX_VALUE)
                .addComponent(_progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(_statusAnimationLabel)
                .addContainerGap())
        );
        _statusPanelLayout.setVerticalGroup(
            _statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(_statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(_statusMessageLabel)
                    .addComponent(_statusAnimationLabel)
                    .addComponent(_progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(_mainPanel);
        setMenuBar(_menu);
        setStatusBar(_statusPanel);
    }// </editor-fold>//GEN-END:initComponents

   private void _searchForKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event__searchForKeyPressed
      if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
         searchIndex();
      }
   }//GEN-LAST:event__searchForKeyPressed

   @Action
   public void loadFile() {
      JFrame mainFrame = Main.getApplication().getMainFrame();
      IndexViewDialog ivd = new IndexViewDialog(mainFrame, true);
      ivd.setLocationRelativeTo(mainFrame);
      ivd.setVisible(true);
      this._index = ivd.getIndex();
      //FIXME pull the index value out from the dialog
   }

   @Action
   public void createFile() {
      JFrame parentFrame = Main.getApplication().getMainFrame();
      IndexCreateDialog icd = new IndexCreateDialog(parentFrame, true);
      icd.setLocationRelativeTo(parentFrame);
      icd.setVisible(true);
      this._index = icd.getIndex();
   }

   @Action
   public void searchIndex() {
      String what = this._searchFor.getText();
      DefaultTableModel dlmindex = ((DefaultTableModel)this._searchResults.getModel());
      dlmindex.setRowCount(0);
      for(IndexedString is : this._index.findAnyInputMatches(what)) {
         dlmindex.addRow(new Object[]{is.getIndex(), is.toString()});
      }
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem _createMenuItem;
    private javax.swing.JMenuItem _loadMenuItem;
    private javax.swing.JPanel _mainPanel;
    private javax.swing.JMenuBar _menu;
    private javax.swing.JProgressBar _progressBar;
    private javax.swing.JMenuItem _saveMenuItem;
    private javax.swing.JButton _searchButton;
    private javax.swing.JTextField _searchFor;
    private javax.swing.JTable _searchResults;
    private javax.swing.JScrollPane _searchResultsPane;
    private javax.swing.JLabel _statusAnimationLabel;
    private javax.swing.JLabel _statusMessageLabel;
    private javax.swing.JPanel _statusPanel;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables
   private final Timer messageTimer;
   private final Timer busyIconTimer;
   private final Icon idleIcon;
   private final Icon[] busyIcons = new Icon[15];
   private int busyIconIndex = 0;
   private JDialog aboutBox;
   private IndexList _index = null;
}
