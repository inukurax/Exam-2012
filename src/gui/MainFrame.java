package gui;

import gui.language.Language;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/** The Regneark main frame.
 * <p>
 * Regneark has just one main frame, hence the singleton implementation. */
public final class MainFrame
    extends JFrame {

  public static final long serialVersionUID = 1L;

  public static final MainFrame instance = new MainFrame();

  private MainFrame() {
    super(Language.instance.regneark());
    this.setJMenuBar(MenuBar.instance);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setContentPane(new MainPanel());
    
    this.pack();
    this.setLocationRelativeTo(null);
  }

  private class MainPanel
      extends JPanel {

    public static final long serialVersionUID = 1L;

    public MainPanel() {
      super(new BorderLayout());
      this.add(ExpressionView.instance, BorderLayout.PAGE_START);
      this.add(TabbedView.instance, BorderLayout.CENTER);
      this.add(StatusView.instance, BorderLayout.PAGE_END);
    }

  }

}
