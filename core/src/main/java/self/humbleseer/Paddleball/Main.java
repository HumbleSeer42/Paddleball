package self.humbleseer.Paddleball;

import java.util.Random;
import java.util.random.RandomGenerator;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener {
    // Important stuff
    Sprite paddle;
    Sprite ball;
    Rectangle paddleRect;
    Rectangle ballRect;
    SpriteBatch sb;
    Texture paddleTexture;
    Texture ballTexture;
    Float dx;
    Float dy;
    RandomGenerator r = new Random();
    Label l;
    LabelStyle ls;

    public Float setvel() {
        Float v = r.nextFloat(1f, 5f);

        return v;
    }

    @Override
    public void create() {
        // Prepare your application here.
        sb = new SpriteBatch();
        paddleTexture = new Texture("paddle.png");
        ballTexture = new Texture("ball.png");
        paddle = new Sprite(paddleTexture);
        ball = new Sprite(ballTexture);
        paddleRect = new Rectangle();
        ballRect = new Rectangle();
        BitmapFont bmf = new BitmapFont();
        ls = new LabelStyle(bmf, Color.BLACK);
        l = new Label("Game Over!", ls);
        l.setPosition(250f, 250f);

        paddle.setSize(150f, 30f);
        paddle.setX(250f);
        paddle.setY(10f);

        ball.setX(250f);
        ball.setY(250f);

        dx = 1.0f;
        dy = 1.0f;
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your application here. The parameters represent the new window size.
    }

    @Override
    public void render() {
        // Draw your application here.
        draw();
        input(paddle);
        moveBall();
        checkHitPaddle();
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void dispose() {
        // Destroy application's resources here.
    }

    public void moveBall() {
        ball.setX(ball.getX() + dx);
        ball.setY(ball.getY() + dy);

        if (ball.getY() > 430f) {
            dy = -1f * setvel();
        } else if (ball.getX() > 590f) {
            dx = -1f * setvel();
        } else if (ball.getY() < 0f) {
            dy = 0f;
            dx = 0f;
        } else if (ball.getX() < 0f) {
            dx = 1f * setvel();
        }
    }

    public void checkHitPaddle() {
        Float paddleWidth = paddle.getWidth();
        Float paddleHeight = paddle.getHeight();
        Float ballWidth = ball.getWidth();
        Float ballHeight = ball.getHeight();

        paddleRect.set(paddle.getX(), paddle.getY(), paddleWidth, paddleHeight);
        ballRect.set(ball.getX(), ball.getY(), ballWidth, ballHeight);

        if (ballRect.overlaps(paddleRect)) {
            dy = 1f * setvel();
        }
    }

    private void draw() {
        sb.begin();

        ScreenUtils.clear(Color.GREEN);

        paddle.draw(sb);

        ball.draw(sb);

        if (ball.getY() < 0f) {
            l.draw(sb, 1.0f);
        }

        sb.end();
    }

    private void input(Sprite paddle) {
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            if (paddle.getX() > 489.9) {
                // pass
            } else {
                paddle.setX(paddle.getX() + 5);
            }
        } else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            if (paddle.getX() < 0.1) {
                // pass
            } else {
                paddle.setX(paddle.getX() - 5);
            }
        }
    }
}
