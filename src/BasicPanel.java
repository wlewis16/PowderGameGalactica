//Imports for graphics:
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class BasicPanel extends JPanel{

    private List<List<Point>> curves = new ArrayList<>();
    private final List<Point> drawnPoints = new ArrayList<>();

    private int dotDiameter = 4;
    private int dotRad = dotDiameter / 2;

    public BasicPanel(){

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                var curve = new ArrayList<Point>();
                curve.add(new Point(e.getX(), e.getY()));
                curves.add(curve);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                curves.getLast().add(new Point(e.getX(), e.getY()));
                repaint(0, 0, getWidth(), getHeight());
            }
        });
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawOval(150, 100, 100, 100);

        g.drawLine(200, 150, 200, 150);

        for(var curve: curves){
            var prevPoint = curve.getFirst();
            for(var point: curve){

                int a = 0;
                int b = 0;
                if(point.x >= prevPoint.x){
                    a = (point.x) - prevPoint.x;
                    if(point.y >= prevPoint.y){
                        b = (point.y) - prevPoint.y;
                    }
                    else{
                        b = prevPoint.y - (point.y);
                    }
                }
                else{
                    a = prevPoint.x - (point.x);
                    if(point.y >= prevPoint.y){
                        b = (point.y) - prevPoint.y;
                    }else{
                        b = prevPoint.y - (point.y);
                    }
                }

                boolean intersectFound = false;
                for(var p: drawnPoints){
                    int aTwo = 0;
                    int bTwo = 0;
                    if(point.x >= p.x){
                        aTwo = (point.x) - p.x;
                        if(point.y >= p.y){
                            bTwo = (point.y) - p.y;
                        }
                        else{
                            bTwo = p.y - (point.y);
                        }
                    }
                    else{
                        aTwo = p.x - (point.x);
                        if(point.y >= p.y){
                            bTwo = (point.y) - p.y;
                        }else{
                            bTwo = p.y - (point.y);
                        }
                    }

                    int distance = (int)Math.sqrt((aTwo*aTwo) + (bTwo*bTwo));
                    if(distance < dotDiameter){
                        intersectFound = true;
                        break;
                    }
                }

                int distBetweenCenters = (int) Math.sqrt((a*a) + (b*b));
                System.out.println(drawnPoints);

                if(distBetweenCenters >= dotDiameter && !intersectFound) {
                    //g.drawOval(point.x - dotRad, point.y - dotRad, dotDiameter, dotDiameter);
                    prevPoint = point;
                    drawnPoints.add(point);
                }

                if(drawnPoints.contains(point)){
                    g.drawOval(point.x - dotRad, point.y - dotRad, dotDiameter, dotDiameter);
                }
            }
        }
    }

}
