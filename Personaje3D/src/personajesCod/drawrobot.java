/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personajesCod;

/**
 *
 * @author carlo
 */
import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class drawrobot {

    //precision and global variables
    private static final int SLICES = 40;
    private static final int STACKS = 40;
    private GLUquadric q = null;
    private static int mvt = 0;

    //heigth and widht of each components
    private static final float HEIGHT_BODY = 0.9f;
    private static final float TOP_BODY = 0.37f;
    private static final float BOTTOM_BODY = 0.25f;
    private static final float HEIGHT_LEGS = 0.2f;
    private static final float WIDTH_LEGS = 0.185f;
    private static final float HEIGHT_ARMS = 0.4f;
    private static final float WIDTH_ARMS = 0.1f;
    private static final float WIDTH_SHOES = 0.28f;
    private static final float HEIGHT_SHOES = 0.05f;
    private static final float WIDTH_PUPILS = 0.03f;

    //position of each component int the window
    public drawrobot() {
    }

    public void draw_caja(GL gl, GLU glu) {
        set_blue_material(gl);
        gl.glPushMatrix();
        box(gl);
        gl.glPopMatrix();
    }

    public void draw_robot(GL gl, boolean walk, boolean jump, boolean mano, boolean firme, boolean arco) {

        GLU glu = new GLU();
        q = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(q, GLU.GLU_FILL);
        glu.gluQuadricOrientation(q, GLU.GLU_OUTSIDE);
        glu.gluQuadricNormals(q, GLU.GLU_SMOOTH);

        //robot is walking
        if (walk && mvt % 20 + 10 > 20) {
            draw_legs(gl, glu, 'W', false);
            draw_legs(gl, glu, ' ', true);
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, 'W');
            draw_head(gl, glu, false, false, false, false);
        } else if (walk && mvt % 20 + 10 <= 20) {
            draw_legs(gl, glu, ' ', false);
            draw_legs(gl, glu, 'W', true);
            draw_arm_left(gl, glu, 'W');
            draw_arm_right(gl, glu, ' ');
            draw_head(gl, glu, false, false, false, false);
        } //robot is jumping
        else if (jump && mvt % 20 + 10 > 20) {
            gl.glTranslatef(0f, 0.35f, 0f);
            draw_legs(gl, glu, 'J', false);
            draw_legs(gl, glu, 'J', true);
            draw_arm_left(gl, glu, 'J');
            draw_arm_right(gl, glu, 'J');
            draw_head(gl, glu, true, false, false, false);
        } //robot is mano
        else if (mano) {
            draw_legs(gl, glu, ' ', false);
            draw_legs(gl, glu, ' ', true);
            draw_arm_left(gl, glu, 'Q');
            draw_arm_right(gl, glu, 'Q');
            draw_head(gl, glu, true, true, false, false);
        }//robot es firme
        else if (firme) {
            draw_legs(gl, glu, ' ', false);
            draw_legs(gl, glu, ' ', true);
            draw_arm_left(gl, glu, 'K');
            draw_arm_right(gl, glu, 'K');
            draw_head(gl, glu, false, false, true, false);
        }//ROBOT ES ARCO
        else if (arco) {
            draw_legs(gl, glu, 'M', false);
            draw_legs(gl, glu, ' ', true);
            draw_arm_left(gl, glu, 'M');
            draw_arm_right(gl, glu, ' ');
            draw_head(gl, glu, false, false, false, true);
        }//robot is normal
        else {
            draw_legs(gl, glu, ' ', false);
            draw_legs(gl, glu, ' ', true);
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, ' ');
            draw_head(gl, glu, false, false, false, false);
        }

        mvt++;
        draw_body(gl, glu);

    }

    public void draw_body(GL gl, GLU glu) {

        //we create robot body
        set_blue_material(gl);
        gl.glPushMatrix();
        gl.glRotatef(90f, 1f, 0f, 0f);
        glu.gluCylinder(q, TOP_BODY, BOTTOM_BODY, HEIGHT_BODY, SLICES, STACKS);
        glu.gluDisk(q, 0f, TOP_BODY, SLICES, STACKS);
        gl.glRotatef(90f, -1f, 0f, 0f);
        gl.glTranslatef(0.0f, -HEIGHT_BODY, 0f);
        gl.glRotatef(90f, 1f, 0f, 0f);
        glu.gluDisk(q, 0f, BOTTOM_BODY, SLICES, STACKS);
        gl.glPopMatrix();
    }

    public void draw_head(GL gl, GLU glu, boolean jump, boolean mano, boolean firme, boolean arco) {

        //we create head
        set_black_material(gl);
        gl.glPushMatrix();
        gl.glRotatef(180f, -1f, 0f, 0f);
        gl.glScalef(-1f, -1f, 0.1f);
        gl.glTranslatef(-0.5f, 0f, 0f);
        box(gl);
        gl.glPopMatrix();

        if (jump == true) {
            //cierra ojos
            set_green_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.1f, 0.6f, -0.1f);
            gl.glRotatef(180f, 0f, 0f, 1f);
            gl.glScalef(0.18f, 0.035f, 0.1f);
            box(gl);
            gl.glPopMatrix();

            set_green_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.01f, 0.30f, 0.09f);
            glu.gluSphere(q, WIDTH_PUPILS, SLICES, STACKS);
            gl.glPopMatrix();
        } else {
            set_green_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.07f, 0.6f, -0.015f);
            glu.gluSphere(q, WIDTH_PUPILS, SLICES, STACKS);
            gl.glTranslatef(0.14f, 0f, 0f);
            glu.gluSphere(q, WIDTH_PUPILS, SLICES, STACKS);
            gl.glPopMatrix();

            //boca
            set_green_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.1f, 0.35f, 0.01f);
            gl.glRotatef(180f, 1f, 0f, 0f);
            gl.glScalef(-0.2f, 0.03f, 0.1f);
            box(gl);
            gl.glPopMatrix();

        }

        if (mano == true) {
            //saca lengua
            set_green_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.01f, 0.30f, 0.09f);
            glu.gluSphere(q, WIDTH_PUPILS, SLICES, STACKS);
            gl.glPopMatrix();
        }
        if (arco == true) {
            //inquitud
            set_green_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.1f, 0.7f, -0.1f);
            gl.glRotatef(180f, 0f, 0f, 1f);
            gl.glScalef(0.2f, 0.03f, 0.1f);
            box(gl);
            gl.glPopMatrix();

            set_green_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.1f, 0.7f, -0.1f);
            gl.glRotatef(180f, 1f, 0f, 0f);
            gl.glScalef(-0.2f, 0.03f, 0.1f);
            box(gl);
            gl.glPopMatrix();
        }

        if (firme == true) {
            //nervioso
            set_green_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.1f, 0.7f, 0.01f);
            gl.glRotatef(-90f, 1f, 0f, 0f);
            gl.glScalef(-0.1f, 0.015f, 0.05f);
            box(gl);
            gl.glPopMatrix();

            set_green_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.2f, 0.7f, 0.05f);
            glu.gluSphere(q, WIDTH_PUPILS, SLICES, STACKS);
            gl.glPopMatrix();
        }

    }

    public void draw_legs(GL gl, GLU glu, char c, boolean left) {
        gl.glPushMatrix();

        //we orientate axes if stan is jumping or is walking
        if (c == 'W') {
            gl.glTranslatef(0f, -0.1f, -0.2f);
            gl.glRotatef(30, -100f, 0f, 0f);
        }

        if (c == 'J') {
            gl.glTranslatef(0f, -0.05f, -0.1f);
            if (left) {
                gl.glRotatef(30, -100f, -100f, 0f);
            } else {
                gl.glRotatef(30, -100f, 100f, 0f);
            }
        }

        if (c == 'M') {
            gl.glTranslatef(0f, -0.1f, -0.2f);
            gl.glRotatef(60, -100f, 0f, 0f);
        }

        //we create legs
        set_yellow_material(gl);
        gl.glPushMatrix();
        if (left) {
            gl.glTranslatef(-0.19f, -1.01f, 0f);
        } else {
            gl.glTranslatef(0.19f, -1.01f, 0f);
        }
        gl.glRotatef(90f, 1f, 0f, 0f);
        glu.gluCylinder(q, WIDTH_LEGS, WIDTH_LEGS, HEIGHT_LEGS, SLICES, STACKS);
        glu.gluSphere(q, WIDTH_LEGS, SLICES, STACKS);
        gl.glRotatef(90f, -1f, 0f, 0f);
        gl.glTranslatef(0f, -HEIGHT_LEGS, 0f);
        gl.glRotatef(90f, 1f, 0f, 0f);
        glu.gluDisk(q, 0f, WIDTH_LEGS, SLICES, STACKS);
        gl.glPopMatrix();

        //we create shoes
        set_red_material(gl);
        gl.glPushMatrix();
        if (left) {
            gl.glTranslatef(-0.34f, -1.3f, -0.15f);
            gl.glScalef(WIDTH_SHOES, HEIGHT_SHOES, 0.25f);
        } else {
            gl.glTranslatef(0.34f, -1.3f, -0.15f);
            gl.glScalef(-WIDTH_SHOES, HEIGHT_SHOES, 0.25f);
        }
        box(gl);
        gl.glPopMatrix();
        gl.glPushMatrix();
        if (left) {
            gl.glTranslatef(-0.2f, -1.25f, 0.12f);
        } else {
            gl.glTranslatef(0.2f, -1.25f, 0.12f);
        }

        gl.glPopMatrix();

        
    }

    public void draw_arm_left(GL gl, GLU glu, char c) {
        set_yellow_material(gl);
        gl.glPushMatrix();
        //we orientate axes if stan is walking or is jumping
        if (c == 'J') {
            gl.glTranslatef(-0.47f, 0.15f, -0.01f);

            gl.glTranslatef(0.05f, 0.015f, 0.05f);

        }
        if (c == 'W') {
            gl.glTranslatef(-0.45f, -0.38f, 0.1f);

            gl.glTranslatef(0.05f, 0.015f, 0.05f);

        }
        if (c == 'Q') {
            gl.glTranslatef(-0.47f, 0.15f, -0.01f);

            gl.glTranslatef(0.05f, 0.015f, 0.05f);

        }

        if (c == 'K') {
            gl.glTranslatef(-0.47f, 0.15f, -0.01f);

            gl.glTranslatef(0.05f, 0.015f, 0.05f);
        }

        if (c == 'M') {
            gl.glTranslatef(-0.47f, 0.15f, -0.01f);

            gl.glTranslatef(0.05f, 0.015f, 0.05f);
        }

        gl.glPopMatrix();

        //we create left arm
        set_yellow_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(-0.37f, -0.125f, 0f);
        gl.glRotatef(90f, 1f, -0.20f, 0f);
        if (c == 'J') {
            gl.glRotatef(150, 0f, -100f, 0f);
        }
        if (c == 'W') {
            gl.glRotatef(20, -1f, 0f, 0f);
        }
        if (c == 'Q') {
            gl.glRotatef(45, 0f, -100f, 0f);
        }
        if (c == 'K') {
            gl.glRotatef(90, 0f, -100f, 0f);
        }
        if (c == 'M') {
            gl.glRotatef(120, 0f, -100f, 0f);
        }
        glu.gluCylinder(q, WIDTH_ARMS, WIDTH_ARMS, HEIGHT_ARMS, SLICES, STACKS);

        gl.glRotatef(90f, -1f, 0.20f, 0f);
        gl.glTranslatef(0f, -HEIGHT_ARMS, 0f);
        gl.glRotatef(90f, 1f, -0.20f, 0f);
        if (c != 'J') {
            glu.gluDisk(q, 0f, WIDTH_ARMS, SLICES, STACKS);
        }
        gl.glPopMatrix();
    }

    public void draw_arm_right(GL gl, GLU glu, char c) {

        set_yellow_material(gl);
        gl.glPushMatrix();

        //we orientate axes if stan is walking or is jumping
        if (c == 'J') {
            gl.glTranslatef(0.47f, 0.15f, -0.01f);

            gl.glTranslatef(-0.05f, 0.015f, 0.05f);

        }
        if (c == 'W') {
            gl.glTranslatef(0.45f, -0.38f, 0.1f);

            gl.glTranslatef(-0.05f, 0.015f, 0.05f);

        }
        if (c == 'Q') {
            gl.glTranslatef(0.47f, 0.15f, -0.01f);

            gl.glTranslatef(-0.05f, 0.015f, 0.05f);

        }
        if (c == 'K') {
            gl.glTranslatef(0.47f, 0.15f, -0.01f);

            gl.glTranslatef(-0.05f, 0.015f, 0.05f);

        }

        gl.glPopMatrix();
        //we create right arm
        set_yellow_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.37f, -0.125f, 0f);
        gl.glRotatef(90f, 1f, 0.20f, 0f);
        if (c == 'J') {
            gl.glRotatef(150, 0f, 100f, 0f);
        }
        if (c == 'W') {
            gl.glRotatef(20, -1f, 0f, 0f);
        }
        if (c == 'Q') {
            gl.glRotatef(120f, 0f, 100f, 0f);
        }
        if (c == 'K') {
            gl.glRotatef(90, 0f, 100f, 0f);
        }

        glu.gluCylinder(q, WIDTH_ARMS, WIDTH_ARMS, HEIGHT_ARMS, SLICES, STACKS);

        gl.glRotatef(90f, -1f, -0.20f, 0f);
        gl.glTranslatef(0f, -HEIGHT_ARMS, 0f);
        gl.glRotatef(90f, 1f, 0.20f, 0f);
        if (c == ' ') {
            glu.gluDisk(q, 0f, WIDTH_ARMS, SLICES, STACKS);
        }
        gl.glPopMatrix();
    }

    public void draw_torus(GL gl, float R, float r, int N, int n) {

        int maxn = 1000;
        n = Math.min(n, maxn - 1);
        N = Math.min(N, maxn - 1);
        float rr = 1.5f * r;
        double dv = 2 * Math.PI / n;
        double dw = 2 * Math.PI / N;
        double v = 0.0f;
        double w = 0.0f;
        while (w < 2 * Math.PI + dw) {
            v = 0.0f;
            gl.glBegin(GL.GL_TRIANGLE_STRIP);
            while (v < 2 * Math.PI + dv) {
                gl.glNormal3d(
                        (R + rr * Math.cos(v)) * Math.cos(w) - (R + r * Math.cos(v)) * Math.cos(w),
                        (R + rr * Math.cos(v)) * Math.sin(w) - (R + r * Math.cos(v)) * Math.sin(w),
                        (rr * Math.sin(v) - r * Math.sin(v)));
                gl.glVertex3d((R + r * Math.cos(v)) * Math.cos(w),
                        (R + r * Math.cos(v)) * Math.sin(w),
                        r * Math.sin(v));
                gl.glNormal3d(
                        (R + rr * Math.cos(v + dv)) * Math.cos(w + dw) - (R + r * Math.cos(v + dv)) * Math.cos(w + dw),
                        (R + rr * Math.cos(v + dv)) * Math.sin(w + dw) - (R + r * Math.cos(v + dv)) * Math.sin(w + dw),
                        rr * Math.sin(v + dv) - r * Math.sin(v + dv));
                gl.glVertex3d((R + r * Math.cos(v + dv)) * Math.cos(w + dw),
                        (R + r * Math.cos(v + dv)) * Math.sin(w + dw),
                        r * Math.sin(v + dv));
                v += dv;
            }
            gl.glEnd();
            w += dw;
        }
    }

    public void set_skin_material(GL gl) {
        float[] mat_ambient = {1.0f, 0.79f, 0.68f, 0.0f};
        float[] mat_diffuse = {0.59f, 0.44f, 0.41f, 0.0f};
        float shine = 128f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void set_shirt_material(GL gl) {

        float mat_ambient[] = {0.5f, 0.45f, 0.3f, 1.0f};
        float[] mat_diffuse = {0.8f, 0.8f, 0.8f, 1.0f};
        float mat_specular[] = {0.4f, 0.3f, 0.2f, 1.0f};
        float shine = 128f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void set_red_material(GL gl) {

        float[] mat_ambient = {0.4f, 0.00f, 0.0f, 0.2f};
        float[] mat_diffuse = {0.4f, 0.0f, 0.0f, 1.0f};
        float[] mat_specular = {0.4f, 0.0f, 0.0f, 1.0f};
        float shine = 15.0f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void set_eyes_material(GL gl) {

        float mat_ambient[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float shine = 51.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void set_blue_material(GL gl) {

        float mat_ambient[] = {0.2f, 0.2f, 0.6f, 1.0f};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float shine = 125.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void set_grey_material(GL gl) {

        float mat_ambient[] = {0.07f, 0.07f, 0.07f, 0.0f};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float shine = 125.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void set_black_material(GL gl) {

        float mat_ambient[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float mat_diffuse[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float mat_specular[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float shine = 125.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
    }

    public void set_green_material(GL gl) {

        float mat_ambient[] = {0.0f, 0.4f, 0.0f, 1.0f};
        float mat_diffuse[] = {0.0f, 0.4f, 0.0f, 1.0f};
        float mat_specular[] = {0.0f, 0.4f, 0.0f, 1.0f};
        float shine = 125.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void set_yellow_material(GL gl) {

        float mat_ambient[] = {0.4f, 0.4f, 0.0f, 1.0f};
        float mat_diffuse[] = {0.4f, 0.4f, 0.0f, 1.0f};
        float mat_specular[] = {0.4f, 0.4f, 0.0f, 1.0f};
        float shine = 125.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }

    public void box(GL gl) {
        gl.glBegin(GL.GL_POLYGON);/* f1: front */
        gl.glNormal3f(-1.0f, 0.0f, 0.0f);
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 0.0f, 0.0f);
        gl.glEnd();
        gl.glBegin(GL.GL_POLYGON);/* f2: bottom */
        gl.glNormal3f(0.0f, 0.0f, -1.0f);
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 0.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glEnd();
        gl.glBegin(GL.GL_POLYGON);/* f3:back */
        gl.glNormal3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(0.0f, 1.0f, 1.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glEnd();
        gl.glBegin(GL.GL_POLYGON);/* f4: top */
        gl.glNormal3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(0.0f, 1.0f, 1.0f);
        gl.glEnd();
        gl.glBegin(GL.GL_POLYGON);/* f5: left */
        gl.glNormal3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(0.0f, 1.0f, 1.0f);
        gl.glVertex3f(0.0f, 0.0f, 1.0f);
        gl.glEnd();
        gl.glBegin(GL.GL_POLYGON);/* f6: right */
        gl.glNormal3f(0.0f, -1.0f, 0.0f);
        gl.glVertex3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 0.0f);
        gl.glEnd();

    }

    public void set_upLight(GL gl) {
        float ligth_ambient[] = {0.2f, 0.2f, 0.2f, 1.0f};
        float ligth_difuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float ligth_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float ligth_position[] = {1.0f, 1.0f, 1.0f, 1.0f};

        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, ligth_ambient, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, ligth_difuse, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, ligth_specular, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, ligth_position, 0);

        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glEnable(GL.GL_DEPTH_TEST);
    }

    public void fondo(GL gl, GLU glu, Texture t) {
        set_upLight(gl);
        int m = t.getTextureObject();
        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glBindTexture(GL.GL_TEXTURE_2D, m);

        gl.glBegin(GL.GL_QUADS);

        gl.glTexCoord2d(0.0f, 1.0f);
        gl.glVertex3f(-6.0f, -6.0f, -6.0f);

        gl.glTexCoord2d(1.0f, 1.0f);
        gl.glVertex3f(6.0f, -6.0f, -6.0f);

        gl.glTexCoord2d(1.0f, 0.0f);
        gl.glVertex3f(6.0f, 6.0f, -6.0f);

        gl.glTexCoord2d(0.0f, 0.0f);
        gl.glVertex3f(-6.0f, 6.0f, -6.0f);

        gl.glEnd();
        gl.glFlush();

        gl.glDisable(GL.GL_TEXTURE_2D);
    }
}
