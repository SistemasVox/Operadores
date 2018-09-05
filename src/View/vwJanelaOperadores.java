package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class vwJanelaOperadores extends JFrame {

	private JPanel contentPane;
	private JTextField txtEntrada;
	JScrollPane scrollPane;
	private JTextArea txtArea;
	int par, impar, dois_em_dois, n;
	ArrayList<Integer> numeros = new ArrayList<Integer>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vwJanelaOperadores frame = new vwJanelaOperadores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public vwJanelaOperadores() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 655, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		txtArea = new JTextArea();
		txtArea.setWrapStyleWord(true);
		txtArea.setLineWrap(true);
		txtArea.setEditable(false);
		txtArea.setFont(new Font("Monospaced", Font.BOLD, 16));
		contentPane.add(scrollPane = new JScrollPane(txtArea));
		scrollPane.setBounds(10, 124, 624, 300);

		JLabel lblV = new JLabel("V: 2.0");
		lblV.setToolTipText("Vers�o do Software");
		lblV.setEnabled(false);
		lblV.setHorizontalAlignment(SwingConstants.RIGHT);
		lblV.setBounds(588, 5, 46, 14);
		contentPane.add(lblV);

		JLabel lblSistemasVox = new JLabel("Sistemas VOX");
		lblSistemasVox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				vwAbout ab = new vwAbout();
				ab.setVisible(true);
			}
		});
		lblSistemasVox
				.setToolTipText("Software Operadores Matemática Discreta. Criado e desenvolvido por Marcelo Vieira,  7º Período. Copyright © 2013-2018 Sistema VOX. Todos os direitos reservados.");
		lblSistemasVox.setBounds(5, 5, 629, 56);
		lblSistemasVox
				.setFont(new Font("Vivaldi", Font.BOLD | Font.ITALIC, 34));
		lblSistemasVox.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblSistemasVox);

		txtEntrada = new JTextField();
		txtEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		txtEntrada.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtEntrada.setBounds(267, 59, 96, 20);
		contentPane.add(txtEntrada);
		txtEntrada.setColumns(10);

		JButton btnDemostrar = new JButton("Demostrar");
		btnDemostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtArea.setText("");
				demostrar();
			}
		});
		btnDemostrar.setBounds(267, 90, 96, 23);
		contentPane.add(btnDemostrar);

		JLabel lblMatemtica = new JLabel("Matem\u00E1tica");
		lblMatemtica.setHorizontalAlignment(SwingConstants.LEFT);
		lblMatemtica.setFont(new Font("Vivaldi", Font.BOLD | Font.ITALIC, 34));
		lblMatemtica.setBounds(0, 59, 267, 43);
		contentPane.add(lblMatemtica);

		JLabel lblDiscreta = new JLabel("Discreta");
		lblDiscreta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiscreta.setFont(new Font("Vivaldi", Font.BOLD | Font.ITALIC, 34));
		lblDiscreta.setBounds(367, 59, 267, 43);
		contentPane.add(lblDiscreta);
	}

	protected void demostrar() {
		par = 0;
		impar = 0;
		dois_em_dois = 0;
		try {
			n = Integer.parseInt(txtEntrada.getText().toString());
			if (n > 0) {
				if ((n * (n + 1)) % 4 == 0) {
					formarEquacao(n);
				} else {
					combinarErro();
				}
			} else {
				JOptionPane
						.showMessageDialog(null,
								"Oppa Digite apenas números naturais inteiros positivos.");
				txtEntrada.setText("");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Oppa Digite apenas números naturais inteiros positivos.");
			txtEntrada.setText("");
		}

	}

	private void combinarErro() {
		String s = "Não é posssível que a equação tenha resultado Zero pois contém um número Ímpar de Ímpares, veja: ";
		String p = "\nPares:";
		String imp = "\nÍmpares:";
		String equa = "\n\nEquação: ";
		int contImpar = 0;

		formarEquacao(n);

		for (int i = 0; i < numeros.size(); i++) {
			if (numeros.get(i) % 2 == 0) {
				p = p + "| " + numeros.get(i);
			} else {
				imp = imp + "| " + numeros.get(i);
				contImpar++;
			}
		}
		p = p + "|.";
		imp = imp + "|.";

		for (int i = 0; i < numeros.size(); i++) {
			equa = equa + formaPositivoENegativo(numeros.get(i)) + " ";
		}
		equa = equa + "= " + somaListaNumeros();
		txtArea.setText(s + p + imp + equa);
		JOptionPane.showMessageDialog(null, String.valueOf(contImpar)
				+ " Ímpares encontrados.");
	}

	private String somaListaNumeros() {
		int soma = 0;
		for (int i = 0; i < numeros.size(); i++) {
			soma = soma + numeros.get(i);
		}
		return String.valueOf(soma);
	}

	protected void formarEquacao(int n) {
		numeros.clear();
		numeros.add(1);
		int pular = 0;
		if (n % 2 == 0) {
			for (int i = 1; i < n; i++) {
				if (dois_em_dois % 2 == 0) {
					numeros.add((i + 1) * -1);
					
					pular++;
					if (pular >= 2) {
						dois_em_dois++;
						pular = 0;
					}
				} else {
					numeros.add(i + 1);
					pular++;
					if (pular >= 2) {
						dois_em_dois++;
						pular = 0;
					}
				}
			}
		} else {
			for (int i = 1; i < n; i++) {
				if (i % 2 == 0) {
					if (par % 2 == 0) {
						numeros.add((i + 1) * -1);
					} else {
						numeros.add(i + 1);
					}
					par++;
				} else {
					if (impar % 2 == 0) {
						numeros.add(i + 1);
					} else {
						numeros.add((i + 1) * -1);
					}
					impar++;
				}
			}
		}
		imprimir();
	}

	private void imprimir() {
		String x = "";
		for (int i = 0; i < numeros.size(); i++) {
			x = x + formaPositivoENegativo(numeros.get(i)) + " ";
		}
		txtArea.setText(x + "= " + somaListaNumeros());
	}

	private String formaPositivoENegativo(Integer i) {
		String s = String.valueOf(i);
		if (i > 0) {
			return s = "+ " + s.substring(0, s.length());
		} else {
			return s.substring(0, 1) + " " + s.substring(1, s.length());
		}
	}
}
