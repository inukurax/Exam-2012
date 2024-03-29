import spreadsheet.Application;
import spreadsheet.Expression;
import spreadsheet.History;
import spreadsheet.Position;
import spreadsheet.Range;
import spreadsheet.Reference;
import spreadsheet.arithmetic.Add;
import spreadsheet.arithmetic.Int;
import spreadsheet.arithmetic.Sum;
import spreadsheet.command.Set;
import spreadsheet.exception.CycleException;
import spreadsheet.textual.Concat;
import spreadsheet.textual.Text;
import ui.PositionInterpreter;
import ui.RangeInterpreter;
import ui.exception.InvalidPosition;

class Main {

  public static void main(String[] _) throws CycleException,
      InvalidPosition {
	  Position posA = new Position(0,0);
	  Position posB = new Position(1,1);

    System.out.println(posA.getDescription());
    System.out.println(posB.getDescription());
    System.out.println(RangeInterpreter.interpret("A0:B1").getDescription());
    Range range = new Range(posA, posB);
    System.out.println(range.getDescription());

    System.out.println(PositionInterpreter.interpret("A0"));
    System.out.println(PositionInterpreter.interpret("B1"));

    Application spreadsheet = Application.instance;
    spreadsheet.set(posA,
      new Concat(new Text("hello, "), new Add(new Int(4), new Int(5))));

    spreadsheet.set(posB, new Int(4));
    spreadsheet.set(new Position(3,2), new Sum (new Reference(spreadsheet.getWorksheet(), new Range(posA, posB))));

    spreadsheet.set(new Position(10, 1),
      new Reference(spreadsheet.getWorksheet(), new Position(10, 2)));
    spreadsheet.set(new Position(10, 2),
      new Reference(spreadsheet.getWorksheet(), new Position(10, 3)));
   Expression test = spreadsheet.get(new Position(3, 2));
   System.out.println(test.getDescription());
   
   // test for undo
  Set first = new Set(new Position(2,5), new Int(10));
  first.execute();

   Expression test4 = spreadsheet.get(new Position(2,5));
   System.out.println("10 = " + test4.getDescription());
   Set second = new Set(new Position(2,5), new Int(8));
   second.execute();

   Expression test2 = spreadsheet.get(new Position(2,5));
   System.out.println(test2.getDescription());

   System.out.println("undo");
   History.instance.pop().undo();
   Expression test3 = spreadsheet.get(new Position(2,5));
   System.out.println(test3.getDescription());
   History.instance.pop().undo();
   Expression test5 = spreadsheet.get(new Position(2,5));

   System.out.println(test5.getDescription());


  }

}
