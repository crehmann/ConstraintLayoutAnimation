package animation.constrainlayout.sample.com.constrainlayoutanimationdemo

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View

class MonkeyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monkey)
        configureAnimation()
    }

    private fun configureAnimation() {
        var targetConstraintsApplied = false
        val layout = findViewById<ConstraintLayout>(R.id.layout)

        val transition = ChangeBounds()
        transition.interpolator = FastOutSlowInInterpolator()

        val initialConstraints = ConstraintSet()
        initialConstraints.clone(layout)

        val targetConstrains = ConstraintSet()
        targetConstrains.clone(this, R.layout.activity_monkey_detail)

        findViewById<View>(R.id.layout).setOnClickListener {
            TransitionManager.beginDelayedTransition(layout, transition)
            val constraint = if (targetConstraintsApplied) initialConstraints else targetConstrains
            constraint.applyTo(layout)
            targetConstraintsApplied = !targetConstraintsApplied
        }
    }
}