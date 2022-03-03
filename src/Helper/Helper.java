package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "İptal");
		UIManager.put("OptionPane.noButtonText", "Hayır");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
	}

	public static void showMessage(String warning) {
		String message;
		optionPaneChangeButtonText();
		switch (warning) {
		case "fill_up":
			message = "Lütfen tüm alanları doldurunuz!";
			break;
		case "successfully":
			message = "İşlem başarılı!";
			break;
		case "Error":
			message = "Bir hata gerçekleşti!";
			break;
		default:
			message = warning;
		}
		JOptionPane.showMessageDialog(null, message, "Mesaj", JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean confirm(String warning) {
		optionPaneChangeButtonText();
		String message;
		switch (warning) {
		case "sure":
			message = "Bu işlemi gerçekleştirmek istiyor musunuz?";
			break;
		default:
			message = warning;
			break;
		}
		int result = JOptionPane.showConfirmDialog(null, message, "Dikkat!", JOptionPane.YES_NO_OPTION);
		if (result == 0) {
			return true;
		} else {
			return false;
		}
	}
}
