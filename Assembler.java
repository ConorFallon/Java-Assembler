import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
/**
 * Write the java code for an Assembler!!!!
 * @author (Conor Fallon & Zach) 
 * @version (11/13/17)
 */
public class Assembler
{
    // Declare arrayList of Strings called labels, to store labels only.
    public static ArrayList <String[]> labels = new ArrayList<String[]>();

    // Declare arrayList of Strings called instructions, to store instruction sets that don't contain labels.
    public static ArrayList <String> instructions = new ArrayList<String>();

    // Declare arrayList of Strings called allLines, to store all instruction sets from the input file.
    public static ArrayList <String> allLines = new ArrayList<String>();

    // Declare arrayList of Strings called outLines, to store our final output lines.
    public static ArrayList <String> outLines = new ArrayList<String>();

    // Declare a String called line for going through the input file line by line.
    public static String line;

    /*
     * A method to read in the text file and sort each line into their corresponding ArrayList.
     * Has printing and debugging statements commented out. You can easily uncomment for better readability.
     * Throws a FileNotFoundException if the input file is not in the project directory.
     */
    private static void readFileAndSort()
    {
        String fileName = "input.txt"; // Assume text file will be called "input" and store it in a String.

        Scanner inFile = null; // Declare and set scanner to null as default.
        try
        {
            inFile = new Scanner(new File(fileName)); // Initialize scanner with the input file.
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Cannot open " + fileName + ". Does it exist in this project directory?"); // Print likely error.
            return;
        }
        System.out.println("File contents with added readability: "); // For readability.
        System.out.println(); // For readability.
        int lineCounter = 0;
        while(inFile.hasNextLine()) // Reading through the input file one line at a time.
        {

            line = inFile.nextLine(); // Store the current line into the String called line.
            // Append our array of all instructions
            allLines.add(line);

            if(line.contains(":")) // Check if current line is a label.
            {
                line = line + ":" + lineCounter;
                labels.add(line.split(":")); // If current line is a label, store it in labels Alist.
            }
            else
            {
                instructions.add(line); // If current line is not a label, store it in instructions Alist.
            }

            lineCounter++;
        }
        // Uncomment below for debugging help.
        /*for(int i = 0; i < labels.size(); i++) // Loop through labels Alist.
        {
        System.out.println("Labels index: " + i +  " " + labels.get(i)); // Print labels element.
        }
        System.out.println(); // For readability.
        for(int j = 0; j < instructions.size(); j++) // Loop through instructions Alist.
        {
        System.out.println("Instructions index: " + j + " " + instructions.get(j)); // Print instructions element.
        }*/
    }

    /*
     * 
     */
    private static String checkFormat(String checkString, String[][] opCodes)
    {
        String format = "Error";
        // Separate out our Op Code to determine appropriate format
        String testStr = checkString;
        // Get rid of label if exists
        if(testStr.contains(":"))
        {
            testStr = testStr.split(":")[1].trim();
        }
        // Pull out the op Code
        testStr = testStr.split(" ")[0];

        // Pull Out condition for B.cond
        if(testStr.contains("."))
        {
            testStr = testStr.split("\\.")[0].trim()+".";
        }

        for(int i = 0; i < opCodes.length; i++)
        {
            // if(testStr.contains(opCodes[i][0]))
            if(testStr.compareTo(opCodes[i][0]) == 0)
            {
                format = opCodes[i][1];
                break;
            }
        }
        return format;
    }

    /*
     * R = 11
     * I = 10
     * CB = 8
     * D = 11
     * B = 6
     */
    private static String[][] buildOpcodes()
    {
        String[][] codeArray = new String[][]
            {
                {"ASR", "rspcl", "10010011010"}, // Opcode 49A
                {"MOV", "rspcl", "10101010000"}, // ORR with XZR
                {"FMULS", "rspcl", "00011110001%000010"},
                {"FDIVS", "rspcl", "00011110001%000110"},
                {"FCMPS", "rspcl", "00011110001%001000"},
                {"ADD", "r", "10001011000"},
                {"ADDS", "r", "10101011000"},
                {"FADDS", "rspcl", "00011110001%001010"},
                {"FSUBS", "rspcl", "00011110001%001110"},
                {"FMULD", "rspcl", "00011110001%000010"},
                {"FDIVD", "rspcl", "00011110001%000110"},
                {"FCMPD", "rspcl", "00011110001%001000"},
                {"FADDD", "rspcl", "00011110001%001010"},
                {"FSUBD", "rspcl", "00011110001%001110"},
                {"SDIV", "rspcl", "10011010110%000010"},
                {"UDIV", "rspcl", "10011010110%000011"},
                {"MUL", "rspcl", "10011011000%011111"},
                {"SMULH", "r", "10011011010"},
                {"UMULH", "r", "10011011110"},
                {"ORRI", "i", "1011001000"},
                {"ORR", "r", "10101010000"},
                {"STURS", "r", "10111100000"},
                {"LDURS", "r", "10111100010"},
                {"EORI", "i", "1101001000"},
                {"EOR", "r", "11001010000"},
                {"LSR", "r", "11010011010"},//****
                {"LSL", "r", "11010011011"},//****
                {"BR", "rspcl", "11010110000"},
                {"SUBS", "r", "11101011000"},
                {"STURD", "r", "11111100000"},
                {"LDURD", "r", "11111100010"},
                {"STURB", "d", "00111000000"},
                {"LDURB", "d", "00111000010"},
                {"STURH", "d", "01111000000"},
                {"LDURH", "d", "01111000010"},
                {"STURW", "d", "10111000000"},
                {"LDURSW", "d", "10111000100"},
                {"LDUR", "d", "11111000010"},
                {"STXR", "d", "11001000000"},
                {"LDXR", "d", "11001000010"},
                {"STUR", "d", "11111000000"},
                {"ADDI", "i", "1001000100"},
                {"ADDIS", "i", "1011000100"},
                {"AND", "r", "10001010000"},
                {"ANDS", "r", "11101010000"},
                {"ANDI", "i", "1001001000"},
                {"ANDIS", "i", "1111001000"},
                {"SUB", "r", "11001011000"},
                {"SUBI", "i", "1101000100"},
                {"SUBIS", "i", "1111000100"},
                {"B.", "cb", "01010100"},
                {"CBZ", "cb", "10110100"},
                {"CBNZ", "cb", "10110101"},
                {"BL", "b", "100101"},
                {"B", "b", "000101"}
            };

        return codeArray;
    }

    /*
     * R format assembly: Opcode, Rd, Rm, Rn
     * R format machine:  Opcode, Rm, Shamt (all 0's), Rn, Rd
     * 
     * R shift format assembly: Opcode, Rd, Rn, Shamt
     * R shift format machine:  Opcode, Rm (all 0's), Shamt, Rn, Rd
     * 
     * CB format assembly: Opcode, Register/Condition, Offset
     * CB format machine:  Opcode, Offset, Register/Condition
     * 
     * D format assembly: Opcode, Rt, Rn, Offset
     * D format machine:  Opcode, Offset, Op, Rn, Rt
     * 
     * I format assembly: Opcode, Rd, Rn, Immediate
     * I format machine:  Opcode, Immediate, Rn, Rd
     * 
     * B format assembly: Opcode, Offset
     * B format machine:  Opcode, Offset
     */
    private static String ParseLine(String setFormat, String [][] opCodes, String [][] condCodes, int currLine)
    {
        String currentFormat, retLine;

        currentFormat = checkFormat(setFormat, opCodes);
        String splitArray [];
        String subSplit [];
        String x;

        // Do replaces for SP, FP, LR and XZR
        setFormat = setFormat.replace("SP", "X28");
        setFormat = setFormat.replace("FP", "X29");
        setFormat = setFormat.replace("LR", "X30");
        setFormat = setFormat.replace("XZR", "X31");

        // Split our string into words
        splitArray = setFormat.split(" ");

        // R format:
        if(currentFormat.equals("r"))
        {
            System.out.println("Format r ");
            System.out.println("Opcode " + splitArray[0] + " " + getBinary(splitArray[0], opCodes));
            System.out.println("RD " + splitArray[1] + " " + stringToBinary(splitArray[1]));
            System.out.println("Rm " + splitArray[2] + " " + stringToBinary(splitArray[2]));
            System.out.println("Rn " + splitArray[3] + " " + stringToBinary(splitArray[3]));
            retLine = getBinary(splitArray[0], opCodes) + stringToBinary(splitArray[2]) + "000000" + stringToBinary(splitArray[3]) + stringToBinary(splitArray[1]);
            return retLine;
        }

        // R special format:
        if(currentFormat.equals("rspcl"))
        {
            System.out.println("Format rspcl ");
            System.out.println("Opcode " + splitArray[0] + " " + getBinary(splitArray[0], opCodes));
            System.out.println("Register " + splitArray[1] + " " + stringToBinary(splitArray[1]));
            if(splitArray.length == 2)
            {
                retLine = getBinary(splitArray[0], opCodes) + String.format("%21s", stringToBinary(splitArray[1]));
                retLine = retLine.replace(" ", "0");
            }

            else if(splitArray.length == 3)
            {
                retLine = getBinary(splitArray[0], opCodes) + stringToBinary(splitArray[2]) + "000000" + "11111"+ stringToBinary(splitArray[1]) ; 
            } else 
            {
                x = getBinary(splitArray[0], opCodes);
                if(x.contains("%"))
                {
                    subSplit = x.split("\\%");
                    retLine =  subSplit[0] + stringToBinary(splitArray[2]) + subSplit[1] + stringToBinary(splitArray[3]) + stringToBinary(splitArray[1]);
                }
                else
                {
                    retLine =  x + "00000" + stringToBinary(splitArray[3]) + stringToBinary(splitArray[2]) + stringToBinary(splitArray[1]);
                }
                // retLine = retLine.replace("%","");
            }
            return retLine;
        }

        // CB format:
        if(currentFormat.equals("cb"))
        {
            if(splitArray[0].contains("."))
            {
                x = splitArray[0];
                subSplit = x.split("\\.");
                System.out.println("Format cb ");
                subSplit[0] = subSplit[0] + ".";
                System.out.println("Opcode " + subSplit[0] + " " + getBinary(subSplit[0], opCodes));
                System.out.println("Condition " + subSplit[1] + " " + getCondBinary(subSplit[1], condCodes));
                System.out.println("Offset text " + expandLabel(splitArray[1]));
                System.out.println("Offset " + labelOffset(splitArray[1], currLine) + " " + stringToBinary(labelOffset(splitArray[1], currLine)));
                retLine = getBinary(subSplit[0], opCodes) + String.format("%19s",stringToBinary(labelOffset(splitArray[1], currLine))) + getCondBinary(subSplit[1], condCodes);
                return retLine.replace(" ", "0");
            }
            else
            {
                System.out.println("Format cb ");
                System.out.println("Opcode " + splitArray[0] + " " + getBinary(splitArray[0], opCodes));
                System.out.println("Register " + splitArray[1] + " " + stringToBinary(splitArray[1]));
                System.out.println("Offset text " + splitArray[2] + " " + expandLabel(splitArray[2]));
                System.out.println("Offset " + splitArray[2] + " " + labelOffset(splitArray[2], currLine) + " " + stringToBinary(labelOffset(splitArray[2], currLine)));
                retLine = getBinary(splitArray[0], opCodes) + String.format("%19s",stringToBinary(labelOffset(splitArray[2], currLine))) + stringToBinary(splitArray[1]);
                return retLine.replace(" ", "0");
            }
        }

        // D format:
        if(currentFormat.equals("d"))
        {
            System.out.println("Format d ");
            System.out.println("Opcode " + splitArray[0] + " " + getBinary(splitArray[0], opCodes));
            System.out.println("RT " + splitArray[1] + " " + stringToBinary(splitArray[1]));
            System.out.println("Rn " + splitArray[2] + " " + stringToBinary(splitArray[2]));
            x = splitArray[3];
            x = x.replace("#", "");
            x = x.replace("]", "");
            System.out.println("Offset " + x + " " + stringToBinary(x));

            if(stringToBinary(x).length() > 9)
            {
                x = stringToBinary(x).substring(stringToBinary(x).length() - 9);
                retLine = getBinary(splitArray[0], opCodes) + x + "00" + stringToBinary(splitArray[2]) + stringToBinary(splitArray[1]);
                return retLine.replace(" ", "0");
            }

            retLine = getBinary(splitArray[0], opCodes) + String.format("%9s",stringToBinary(x)) + "00" + stringToBinary(splitArray[2]) + stringToBinary(splitArray[1]);
            return retLine.replace(" ", "0");
        }

        // I format:
        if(currentFormat.equals("i"))
        {
            System.out.println("Format i ");
            System.out.println("Opcode " + splitArray[0] + " " + getBinary(splitArray[0], opCodes));
            System.out.println("RD " + splitArray[1] + " " + stringToBinary(splitArray[1]));
            System.out.println("Rn " + splitArray[2] + " " + stringToBinary(splitArray[2]));
            System.out.println("Immediate " + splitArray[3] + " " + stringToBinary(splitArray[3]));
            retLine = getBinary(splitArray[0], opCodes) + String.format("%12s",stringToBinary(splitArray[3])) + stringToBinary(splitArray[2]) + stringToBinary(splitArray[1]);
            return retLine.replace(" ", "0");
        }

        // B format:
        if(currentFormat.equals("b"))
        {
            System.out.println("Format b ");
            System.out.println("Opcode " + splitArray[0] + " " + getBinary(splitArray[0], opCodes));
            System.out.println("Offset text " + splitArray[1] + " " + expandLabel(splitArray[1]));
            System.out.println("Offset " + splitArray[1] + " " + labelOffset(splitArray[1], currLine) + " " + stringToBinary(labelOffset(splitArray[1], currLine)));
            retLine = getBinary(splitArray[0], opCodes) + String.format("%26s", stringToBinary(labelOffset(splitArray[1], currLine)));
            return retLine.replace(" ", "0");
        }
        System.out.println("-----------");
        retLine = "Error";
        return retLine;
    }

    /*
     * 
     */
    private static String expandLabel(String theLabel)
    {
        // Declare some variables
        String theTarget = "Error";

        // Find the label in our array
        for(int i = 0; i < labels.size(); i++)
        {
            // if(labels.get(i)[0].equals(theLabel))
            if(theLabel.compareTo(labels.get(i)[0]) == 0)
            {
                theTarget = labels.get(i)[1];
                break;
            }
        }

        return theTarget;
    }

    /*
     * 
     */
    private static String labelOffset(String theLabel, int currLine)
    {
        // Declare some variables
        int theOffset = 0;

        // Find the label in our array
        for(int i = 0; i < labels.size(); i++)
        {
            // if(labels.get(i)[0].equals(theLabel))
            if(theLabel.compareTo(labels.get(i)[0]) == 0)
            {
                theOffset = Integer.parseInt(labels.get(i)[2]) - (currLine + 1);
                break;
            }
        }

        return Integer.toString(theOffset).trim();
    }

    /*
     * 
     */
    private static String getBinary(String theCode, String [][] opCodes)
    {
        // Declare some variables
        String theTarget = "Error";

        // Find the label in our array
        for(int i = 0; i < opCodes.length; i++)
        {
            // if(opCodes[i][0].contains(theCode))
            if(theCode.compareTo(opCodes[i][0]) == 0)
            {
                theTarget = opCodes[i][2];
                break;
            }
        }
        return theTarget;
    }

    /*
     * 
     */
    private static String[][] buildCondCode()
    {
        String [][] condCodes = new String [][]
            {
                {"EQ", "00000"},
                {"NE", "00001"},
                {"HS", "00010"},
                {"CS", "00010"},
                {"LO", "00011"},
                {"LL", "00011"},
                {"MI", "00100"},
                {"PL", "00101"},
                {"VS", "00110"},
                {"VC", "00111"},
                {"HI", "01000"},
                {"LS", "01001"},
                {"GE", "01010"},
                {"LT", "01011"},
                {"GT", "01100"},
                {"LE", "01101"},
                {"AL", "01110"},
                {"NV", "01111"}
            };
        return condCodes;
    }

    /*
     * 
     */
    private static String getCondBinary(String theCode, String [][] condCodes)
    {
        // Declare some variables
        String targetVal = "Error";

        // Find the label in our array
        for(int i = 0; i < condCodes.length; i++)
        {
            if(condCodes[i][0].contains(theCode))
            {
                targetVal = condCodes[i][1];
                break;
            }
        }
        return targetVal;
    }

    /*
     * 
     */
    private static String stringToBinary(String codeToConvert)
    {
        /*
        retFormat determines padding length and character
        rt = pad left with 0 to length of 5
        rr = pad left with 1 to length of 

         */
        String copy = codeToConvert.trim(), retCopy;
        int theNumber, atComma, atBracket;
        // Remove the leading character(s)
        if(copy.contains("["))
        {
            copy = copy.substring(2);
        }
        else if(!Character.isDigit(copy.charAt(0)) && !copy.contains("-"))
        {
            copy = copy.substring(1);
        }
        // Test to see if there is a comma at the end
        atComma = copy.indexOf(",");
        atBracket = copy.indexOf("]");
        if(atComma > 0)
        {
            copy = copy.substring(0,atComma);
        }

        if(atBracket > 0)
        {
            copy = copy.substring(0,atBracket);
        }

        try
        {
            theNumber = Integer.parseInt(copy);
        }
        catch (NumberFormatException e)
        {
            return "Error:" + codeToConvert;
        }

        retCopy = String.format("%5s", Integer.toBinaryString(theNumber));
        retCopy = retCopy.replace(" ", "0");

        if(retCopy.length() > 26)
        {
            retCopy = retCopy.substring(retCopy.length() - 19);
        }
        return retCopy;
    }

    /*
     * 
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        readFileAndSort();

        String [][] opCodes = buildOpcodes();
        String [][] condCodes = buildCondCode();
        String theInstruction;

        System.out.println("First print out the labels");
        System.out.println("**************************");

        for(int i = 0; i < labels.size(); i++) // Loop through labels Alist.
        {
            System.out.println("Label: " + labels.get(i)[0] +  " Code: " + labels.get(i)[1] + " Line: " + labels.get(i)[2]); // Print labels element.
        }

        /*
        System.out.println(); // For readability.
        System.out.println("First print out the instructions");
        System.out.println("**************************");

        for(int j = 0; j < instructions.size(); j++) // Loop through instructions Alist.
        {
        ParseLine(instructions.get(j), opCodes, condCodes);
        }
         */

        System.out.println(); // For readability.
        System.out.println("First print out all lines");
        System.out.println("**************************");

        for(int k = 0; k < allLines.size(); k++) // Loop through instructions Alist.
        {
            if(allLines.get(k).contains(":"))
            {
                theInstruction = allLines.get(k).split(":")[1].trim();
            }
            else
            {
                theInstruction = allLines.get(k);
            }
            outLines.add(ParseLine(theInstruction, opCodes, condCodes, k));
        }

        // Display our final output
        System.out.println();
        System.out.println("Ouput");
        System.out.println();
        System.out.println("00000000011111111112222222222333");
        System.out.println("12345678901234567890123456789012");
        for(int l = 0; l < outLines.size(); l++)
        {
            System.out.println(outLines.get(l));
        }

        // Finally write our output to a text file
        PrintWriter output = new PrintWriter("out.txt");
        
        int lineCount = 0;
        
        //output.println("File Order: ");
        for(int t = 0; t <outLines.size(); t++)
        {
            output.println(outLines.get(t));
        }
        
        output.close();
    }
}