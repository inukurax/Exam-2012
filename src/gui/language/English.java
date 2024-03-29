package gui.language;

/** The English language pack.
 */
final class English extends LanguageSpecification {

  public String regneark() {
    return "Regneark";
  }

  public String file() {
    return "File";
  }

  public String exit() {
    return "Exit";
  }

  public String spreadsheet() {
    return "Spreadsheet";
  }

  public String newSpreadsheet() {
    return "New spreadsheet";
  }

  public String removeSpreadsheet() {
    return "Remove spreadsheet";
  }

@Override
public String edit() {
	return "Edit";
}

@Override
public String undo() {
	return "Undo";
}

@Override
public String copy() {
	return "Copy";
}

@Override
public String paste() {
	return "Paste";
}

@Override
public String data() {
	return "Data";
}

@Override
public String plot() {
	return "Plot";
}

}
