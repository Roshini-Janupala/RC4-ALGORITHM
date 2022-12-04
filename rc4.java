import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
class rc4 extends JFrame implements ActionListener
{
JTextField jt1,jt2,jt3,jt4;
  JButton jb1,jb2;
  JLabel jl1,jl2,jl,jl3,jl5,jl4;
  rc4()
  {
   super("RC4 Alogorithm");
   jl1=new JLabel("Modified RC4 Encryption algorithm");
   jl1.setBounds(200,30,500,50); 
   jl1.setFont(new Font("Italic",Font.PLAIN,30));
   jl1.setForeground(Color.BLUE);
   this.add(jl1);
   
   jl2=new JLabel("Enter Plain Text:");
   jl2.setBounds(50,100,900,100); 
   jl2.setFont(new Font("Times New Roman",Font.PLAIN,24));
   this.add(jl2);
   
   jt1=new JTextField(""); 
   jt1.setBounds(350,120,500,50); 
   this.add(jt1);
   
   
   jl3=new JLabel("Enter Key:");
   jl3.setBounds(50,230,900,100);
   
   jl3.setFont(new Font("Times New Roman",Font.PLAIN,24)); 
   this.add(jl3);
   
   jt2=new JTextField(""); 
   jt2.setBounds(350,250,500,50); 
   this.add(jt2);
   
   jb1=new JButton("Encrypt"); 
   jb1.setBounds(250,380,150,50); 
   this.add(jb1); 
   jb2=new JButton("Decrypt"); 
   jb2.setBounds(450,380,150,50); 
   this.add(jb2); 
   

   jl4=new JLabel(""); 
   jl4.setBounds(180,410,1000,80); 
   jl4.setFont(new Font("Times New Roman",Font.PLAIN,20));
   jl4.setForeground(Color.BLUE);
   this.add(jl4);
   
   
   jl5=new JLabel(""); 
    jl5.setBounds(180,460,1000,80); 
    jl5.setFont(new Font("Times New Roman",Font.PLAIN,20));
    jl5.setForeground(Color.BLACK);
   this.add(jl5);
   
   jl=new JLabel("");
   jl.setForeground(Color.RED);
   jl.setBounds(180,500,500,100); 
   jl.setFont(new Font("Times New Roman",Font.PLAIN,25)); 
   this.add(jl); 
   
   jb1.setBackground(Color.cyan);
   jb2.setBackground(Color.cyan);
   
   jb1.addActionListener(this); 
   jb2.addActionListener(this); 
   
   this.getContentPane().setBackground(Color.yellow);
   this.setSize(1000,1000); 
   this.setLayout(null);
   this.setVisible(true);
   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
  }
 public void actionPerformed(ActionEvent ae)
   {
   try{
   String pltext= jt1.getText();
   String key=jt2.getText();
   jl.setText("");
   jl4.setText("");
   jl5.setText("");
   if(pltext.equals("")||key.equals(""))
   {
   jl.setText("Please enter plain text and key");
   	System.out.println("enter plain text and key");
   }
   else{
   try {
        FileWriter fw=new FileWriter("encryptionfile.txt",true);
		 fw.write("\nPlain Text:\t"+pltext);
	    fw.write("\nKey:\t"+key);
   String tempt="";
   String tempd="";
    int temp=0; 
	int s[]=new int[256]; 
	int T[]=new int[256];
    char pltextch[]=pltext.toCharArray(); 
	int cipher[]=new int[pltext.length()]; 
	int decrypt[]=new int[pltext.length()]; 
	int pltexti[]=new int[pltext.length()];  
	for(int i=0;i<pltext.length();i++) 
	{ 
		pltexti[i]=(int)pltextch[i]; 
	} 
	for(int i=0;i<255;i++) 
	{ 
		s[i]=i; 
	} 
	//rev 
	int i=0; 
	int j=0; 
	char ch;
		String s2=new String();
		
		char SArray[] = new char[key.length()];
		char StrRev[] = new char[key.length()];
		
		SArray= key.toCharArray();                  
	     for(i=0;i<key.length();i++)
	     {
	       ch=key.charAt(i);
	       s2=ch+s2;
	      }
		StrRev= s2.toCharArray();                 
		i=0;
		while(i<256){
			for(j=0; j<SArray.length && i<256; j++){
				T[i]=SArray[j];
				i++;
			}
			for(j=0; j<StrRev.length && i<256; j++){
				T[i]=StrRev[j];
				i++;
			}	
		}
	j=0; 
	for(i=0;i<255;i++) 
	{ 
		j=(j+s[i]+T[i])%256; 
		temp=s[i]; 
		s[i]=s[j]; 
		s[j]=temp; 
	} 

	int keystream=0; 
	for(int l=0;l<pltext.length();l++) 
	{ 
		i=(i+1)%256; 
		j=(j+s[i])%256; 
		temp=s[i]; 
		s[i]=s[j]; 
		s[j]=temp; 
		keystream=s[(s[i]+s[j])%256]; 
		cipher[l]=keystream^pltexti[l]; 
		decrypt[l]=keystream^cipher[l]; 
	} 
	for(i=0;i<pltext.length();i++){
	
	tempt=tempt+(char)cipher[i];
	tempd=tempd+(char)decrypt[i];
     }
     if(ae.getSource()==jb1)
    	jl4.setText("Encrypted text:\t"+tempt);
    if(ae.getSource()==jb2)
    {
	jl5.setText("Decrypted text:\t"+tempd);
	}
    	
    fw.write("\nEncrypted:\t"+tempt);
    fw.close();
    
     }
   catch (IOException e1)
    {
        e1.getMessage();
    }
   }
   }
   catch(Exception e)
   {
   System.out.println(e.getMessage());
   }
     
   }
	public static void main(String args[])throws Exception
	{
	new rc4();
	}
}