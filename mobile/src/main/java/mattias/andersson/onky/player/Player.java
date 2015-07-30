package mattias.andersson.onky.player;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import mattias.andersson.onky.helper.Point2D;
import mattias.andersson.onky.powerup.PowerUp;

/**
 * Created by Alrik on 2015-07-30.
 */
public abstract class Player {
    long trailspawnTimer;
    public ArrayList<PowerUp> usedPowerup = new ArrayList<PowerUp>();
    // PImage ONKYSpriteSheet,Life, cell; //setup
    public float angle, decayFactor= .95f,punchTime, jumpTime, invis, toSlow;
    public Point2D coord= new Point2D(), velocity= new Point2D(), acceleration= new Point2D(), size= new Point2D();
    public final int MAX_LIFE = 3, MAX_JUMP = 2, PUNCH_MAX_CD = 20, SMASH_MAX_CD = 50, defaultSpeed = 10, MAX_POWERUP_SIZE = 16;
    public int cooldown, collectCooldown, jumpHeight = 20, jumpCount = MAX_JUMP, downDashSpeed = 35, lives = MAX_LIFE,punchCooldown = PUNCH_MAX_CD, punchRange = 100, attractRange, stompRange = 150;
    public float duckTime, duckCooldown, duckHeight = 45, tauntTime, smashTime, smashCooldown = SMASH_MAX_CD, smashRange = 100, attckSpeedReduction;
    public boolean taunt = true, dead, onGround, punching, stomping, ducking, invincible, respawning, tokenDroping;
    public int totalJumps, totalAttacks, totalDucks, tutorialCourseRetries;
    public float averageSpeed;
    public Paint color = new Paint(Color.WHITE);
    public Paint defaultWeaponColor = new Paint(Color.WHITE);
    public Paint weaponColor = defaultWeaponColor;
    //   int amountOfFrames=140;

    public void Player() {


    }

    public void update() {

    }

    public void display() {
    }
}
