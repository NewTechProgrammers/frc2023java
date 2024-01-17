// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This sample program shows how to control a motor using a joystick. In the operator control part
 * of the program, the joystick is read and the value is written to the motor.
 *
 * <p>Joystick analog values range from -1 to 1 and motor controller inputs also range from -1 to 1
 * making it easy to work together.
 *
 * <p>In addition, the encoder value of an encoder connected to ports 0 and 1 is consistently sent
 * to the Dashboard.
 */
public class Robot extends TimedRobot 
{
  private int counter = 0;
  private int buttonCounter = 0;
  private int pack = 0;

  private PWMSparkMax m_motor;
  private Joystick m_stick = new Joystick(0);
  private Encoder m_encoder;
  private XboxController xbox_stick = new XboxController(0);

  private VictorSP m_motor0 = new VictorSP(0);
  private VictorSP m_motor1 = new VictorSP(1);
  private VictorSP m_motor2 = new VictorSP(2);
  private VictorSP m_motor3 = new VictorSP(3);
  private VictorSP m_motor4 = new VictorSP(4);
  private VictorSP m_motor5 = new VictorSP(5);
  private int statement1=0;
  private int statement2=0;
  private boolean header = false;

  @Override
  public void teleopPeriodic() 
  {
    double RightX = xbox_stick.getRightX();
    double LeftY = xbox_stick.getLeftY();
    double RightY = xbox_stick.getRightY();

    boolean A = xbox_stick.getAButton();
    boolean B = xbox_stick.getBButton();
    boolean X = xbox_stick.getXButton();
    boolean Y = xbox_stick.getYButton();

    boolean LB = xbox_stick.getLeftBumper();

    double Slider = m_stick.getThrottle();
    double JoystickX = m_stick.getX();
    double JoystickY = m_stick.getY();


    
    boolean button1 = m_stick.getRawButton(1);
    boolean button2 = m_stick.getRawButton(2); 

    boolean button8 = m_stick.getRawButton(8);

    if(button8){header=true;}
    if(header==true)    {
      statement2++;
      
      if(statement2<=50){m_motor4.set(-0.6);m_motor5.set(-0.6);}
      if(statement2>60 && statement2<=180){m_motor4.set(0.3);m_motor5.set(0.3);}
      if(statement2>180 && statement2<=250){m_motor4.set(0.25);m_motor5.set(0.1);}
      if(statement2>250 && statement2<=300){m_motor4.set(0);m_motor5.set(0);}
      if(statement2>=300){header=false; statement2=0;}
      
      if(button1==true){m_motor5.set(0.2); m_motor4.set(0.2);}
    if(button2==true){m_motor5.set(-0.2); m_motor4.set(-0.2);}
 
    // Header
    // if(Slider>=0.5 or Slider<=-0.5){ m_motor6.Set(Slider); }
    // else{m_motor6.Set(0);}
 
    if(JoystickY>=0.3 || JoystickY<=-0.3)
    {
      m_motor4.set(-JoystickY/1.5); m_motor5.set(-JoystickY/1.5); 
    }
    else if(JoystickY<=0.3 && JoystickY>=-0.3 && statement2==0 && statement1==0 && button2==false && button1==false)
    { m_motor4.set(0);
      m_motor5.set(0); }
 
 
    // int option;
 
    /*.______________.Robot ride control._____________.*/
 
    /*.~~~~~~~~~~.Multiplicators.~~~~~~~~~~.*/
    double mul = 0.0;
 
 
    /*.~~~~~~~~~~.Forward and backward.~~~~~~~~~~.*/
    if (LeftY >= 0.25 || LeftY <= -0.25)
    { if (RightX < -0.25){ mul = RightX; }
      if (RightX > 0.25){ mul = RightX; }
      if (RightX < 0.25 && RightX > -0.25){ mul = 0; }
 
      m_motor0.set((-LeftY + mul) / 3);
      m_motor1.set((-LeftY + mul) / 3);
 
      m_motor2.set((LeftY + mul) / 3);
      m_motor3.set((LeftY + mul) / 3); }
 
    /*.~~~~~~~~~~.Turning right and left.~~~~~~~~~~.*/
    if ((RightX >= 0.2 || RightX <= -0.2) && LeftY < 0.2 && LeftY > -0.2)
    { m_motor0.set(RightX / 2);
      m_motor1.set(RightX / 2);
      m_motor2.set(RightX / 2);
      m_motor3.set(RightX / 2); }
    /*.~~~~~~~~~~.Stop.~~~~~~~~~~.*/
    if ((RightX > -0.15 && RightX < 0.15) && (LeftY > -0.15 && LeftY < 0.15))
    { m_motor0.set(0);
      m_motor1.set(0);
      m_motor2.set(0);
      m_motor3.set(0); }
    /*.~~~~~~~~~~.Drag.~~~~~~~~~~.*/
    if (A)
    { m_motor0.set(0);
      m_motor1.set(0);
      m_motor2.set(0);
      m_motor3.set(0); }
    /*.~~~~~~~~~~.TURBO.~~~~~~~~~~.*/
    /*
    if(RT==true and LT==true)
    {
      m_motor1.Set(-1);
      m_motor2.Set(-1);
      m_motor3.Set(1);
      m_motor4.Set(1);
    }*/
 
    /*.______________._________________._____________.*/
 
    System.out.println("This file " + pack++ + "\n");
    System.out.println("statement1: " + statement1 + "\n");
    System.out.println("statement2: " + statement2 + "\n");
    System.out.println("LeftY: " + LeftY + "\n");
    System.out.println("RightY: " + RightY + "\n");
    System.out.println("JoystickX: " + JoystickX + "\n");
    System.out.println("JoystickY: " + JoystickY + "\n");
    System.out.println("Slider: " + Slider + "\n");
    }
  }
}
