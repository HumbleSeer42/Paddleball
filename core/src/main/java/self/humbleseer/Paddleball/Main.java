package self.humbleseer.Paddleball;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener {
    // Important stuff
    Sprite paddle;
    SpriteBatch sb;
    Texture paddleTexture;

    @Override
    public void create() {
        // Prepare your application here.
        sb = new SpriteBatch();
        paddleTexture = new Texture("paddle.png");
        paddle = new Sprite(paddleTexture);

        paddle.setSize(150f, 30f);
        paddle.setX(250f);
        paddle.setY(10f);
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

    private void draw() {
        sb.begin();

        ScreenUtils.clear(Color.GREEN);

        paddle.draw(sb);

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

        System.out.println("x: " + paddle.getX() + ". y: " + paddle.getY());
    }
}
