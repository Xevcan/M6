import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class Act2View extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;
	private JTextField tfContrasenya;
	private JTextField tfIndex;
	private ButtonGroup tableGroup;
	private ButtonGroup actionGroup;
	private Act2 act2 = new Act2();
	private int q = 0;
	String nombre;
	String DNI;
	String fecha;
	String direccion;
	String codigoPostal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Act2View frame = new Act2View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Act2View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfIndex = new JTextField();
		tfIndex.setBounds(155, 230, 221, 20);
		contentPane.add(tfIndex);
		tfIndex.setColumns(10);
		
		JTextPane tpRespuesta = new JTextPane();
		tpRespuesta.setText("...");
		tpRespuesta.setBounds(155, 206, 269, 23);
		contentPane.add(tpRespuesta);
		
		JButton bOK = new JButton("OK");
		bOK.setBounds(377, 230, 57, 20);
		contentPane.add(bOK);
		
		JButton bContinuar = new JButton("Continuar");
		bContinuar.setEnabled(false);
		bContinuar.setBounds(159, 176, 89, 23);
		contentPane.add(bContinuar);
		bContinuar.addActionListener(new ActionListener() {		
  public void actionPerformed(ActionEvent e) {
    		String tabla = tableGroup.getSelection().getActionCommand();
				String accion = actionGroup.getSelection().getActionCommand();

				if (tabla.equals("1") && accion.equals("3")) {
					// delete alumnos
					tpRespuesta.setText("Introduce el DNI del alumno a borrar");
					bOK.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String DNI = tfIndex.getText();
							act2.probarConexion();
							try {
								act2.BorrarAlumnos(DNI);
								act2.cerrarConexion();
								JOptionPane.showMessageDialog(new JFrame(), "El alumno se ha borrado correctamente.", "Aviso",
								        JOptionPane.INFORMATION_MESSAGE);
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(new JFrame(), "No se puede hacer la modificación.", "Error en aplicar",
								        JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
							}
							
						}
					});

				} else if (tabla.equals("1") && accion.equals("4")) {
					//agregar alumnos
					tpRespuesta.setText("Introduce el Nombre");
					q++;
					bOK.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							if (q == 1) {
								nombre = tfIndex.getText();
								tpRespuesta.setText("Introduce el DNI");
								q++;
							}else if (q == 2) {
								nombre = tfIndex.getText();
								tpRespuesta.setText("Introduce Fecha (yyyy-mm-dd)");
								q++;
							} else if (q == 3) {
								fecha = tfIndex.getText();
								tpRespuesta.setText("Introduce Dirección");
								q++;
							} else if (q ==4) {
								direccion = tfIndex.getText();
								tpRespuesta.setText("Introduce codigo postal");
								q++;
							} else if (q == 5) {
								codigoPostal = tfIndex.getText();
								
								act2.probarConexion();
								try {
									act2.generarAlumnos(nombre, DNI, fecha, direccion, codigoPostal);
									act2.cerrarConexion();
									JOptionPane.showMessageDialog(new JFrame(), "El alumno se ha agregado correctamente.", "Aviso",
											JOptionPane.INFORMATION_MESSAGE);
								} catch (SQLException e1) {
									JOptionPane.showMessageDialog(new JFrame(), "No se puede hacer la modificación.", "Error en aplicar",
									        JOptionPane.ERROR_MESSAGE);
									e1.printStackTrace();
								}
								q = 0;
							}
						}
					});
					
				}else if (tabla.equals("2") && accion.equals("3")) {
					// delete poblacion
					tpRespuesta.setText("Introduce el codigo posta de la poblacion");
					bOK.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String codigo = tfIndex.getText();
							act2.probarConexion();
							try {
								act2.BorrarPoblacion(codigo);
								act2.cerrarConexion();
								JOptionPane.showMessageDialog(new JFrame(), "La poblacion se ha borrado correctamente.", "Aviso",
								        JOptionPane.INFORMATION_MESSAGE);
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(new JFrame(), "No se puede hacer la modificación.", "Error en aplicar",
								        JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
							}
							
						}
					});
					
				} else if (tabla.equals("2") && accion.equals("4")){
					//agregar poblacion
					tpRespuesta.setText("Introduce el código postal");
					q++;
					bOK.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							if (q == 1) {
								codigoPostal = tfIndex.getText();
								tpRespuesta.setText("Introduce el nombre");
								q++;
							}else if (q == 2) {
								nombre = tfIndex.getText();
								
								act2.probarConexion();
								try {
									act2.generarPoblacion(codigoPostal, nombre);
									act2.cerrarConexion();
									JOptionPane.showMessageDialog(new JFrame(), "La población se ha agregado correctamente.", "Aviso",
											JOptionPane.INFORMATION_MESSAGE);
								} catch (SQLException e1) {
									JOptionPane.showMessageDialog(new JFrame(), "No se puede hacer la modificación.", "Error en aplicar",
									        JOptionPane.ERROR_MESSAGE);
									e1.printStackTrace();
								}
								q = 0;
							}
						}
					});
			}
    		
  }	
});
		
		JTextPane tpLogin = new JTextPane();
		tpLogin.setBackground(UIManager.getColor("Button.background"));
		tpLogin.setEditable(false);
		tpLogin.setText("Inicia Sesion");
		tpLogin.setBounds(10, 11, 92, 20);
		contentPane.add(tpLogin);
		
		JTextPane tpSelectTabla = new JTextPane();
		tpSelectTabla.setEditable(false);
		tpSelectTabla.setText("Seleccione la tabla");
		tpSelectTabla.setBounds(159, 11, 139, 23);
		contentPane.add(tpSelectTabla);
		
		JRadioButton rdbtnAlumnos = new JRadioButton("Alumnos");
		rdbtnAlumnos.setActionCommand("1");
		rdbtnAlumnos.setEnabled(false);
		rdbtnAlumnos.setBounds(155, 42, 109, 23);
		contentPane.add(rdbtnAlumnos);
		
		JRadioButton rdbtnPoblacion = new JRadioButton("Poblacion");
		rdbtnPoblacion.setActionCommand("2");
		rdbtnPoblacion.setEnabled(false);
		rdbtnPoblacion.setBounds(155, 62, 109, 23);
		contentPane.add(rdbtnPoblacion);
		
		tableGroup = new ButtonGroup();
		tableGroup.add(rdbtnAlumnos);
		tableGroup.add(rdbtnPoblacion);
		
		JTextPane tpSelecAccion = new JTextPane();
		tpSelecAccion.setEditable(false);
		tpSelecAccion.setText("Seleccione la acci\u00F3n");
		tpSelecAccion.setBounds(159, 94, 139, 20);
		contentPane.add(tpSelecAccion);
		
		JRadioButton rdbtnEliminar = new JRadioButton("Eliminar");
		rdbtnEliminar.setActionCommand("3");
		rdbtnEliminar.setEnabled(false);
		rdbtnEliminar.setBounds(155, 126, 109, 23);
		contentPane.add(rdbtnEliminar);
		
		JRadioButton rdbtnAgregar = new JRadioButton("Agregar");
		rdbtnAgregar.setActionCommand("4");
		rdbtnAgregar.setEnabled(false);
		rdbtnAgregar.setBounds(155, 146, 109, 23);
		contentPane.add(rdbtnAgregar);
		
		actionGroup = new ButtonGroup();
		actionGroup.add(rdbtnEliminar);
		actionGroup.add(rdbtnAgregar);
		
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(10, 63, 92, 20);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		tfContrasenya = new JTextField();
		tfContrasenya.setBounds(10, 115, 92, 20);
		contentPane.add(tfContrasenya);
		tfContrasenya.setColumns(10);
		
		JTextPane tpUsuario = new JTextPane();
		tpUsuario.setEditable(false);
		tpUsuario.setText("Usuario:");
		tpUsuario.setBounds(10, 42, 92, 23);
		contentPane.add(tpUsuario);
		
		JTextPane tpContrasenya = new JTextPane();
		tpContrasenya.setEditable(false);
		tpContrasenya.setText("Contrase\u00F1a:");
		tpContrasenya.setBounds(10, 94, 92, 20);
		contentPane.add(tpContrasenya);
		
		
		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Button.disabledForeground"));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(124, 11, 24, 239);
		contentPane.add(separator);
		
		JButton bLogin = new JButton("Iniciar");
		bLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				act2.setUsuario(tfUsuario.getText()+"");
				act2.setPasswrd(tfContrasenya.getText()+"");
				act2.probarConexion();
				
				if (act2.isLogin()) {
					rdbtnAlumnos.setEnabled(true);
					rdbtnPoblacion.setEnabled(true);
					rdbtnAgregar.setEnabled(true);
					rdbtnEliminar.setEnabled(true);
					bContinuar.setEnabled(true);
				}
			}
		});
		bLogin.setBounds(10, 146, 89, 23);
		contentPane.add(bLogin);
		

		
		
	}
	
}
