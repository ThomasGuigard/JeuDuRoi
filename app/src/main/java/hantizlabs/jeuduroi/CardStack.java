package hantizlabs.jeuduroi;

import android.content.res.TypedArray;
import java.util.ArrayList;
import java.util.Queue;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.database.DataSetObserver;

import hantizlabs.jeuduroi.Controller.InitialiseJoueurs;
import hantizlabs.jeuduroi.Model.Carte;


public class CardStack extends RelativeLayout {
    private int mColor = -1;
    private int mIndex = 0;
    private int mNumVisible = 1;
    private boolean canSwipe = true;
    private ArrayAdapter<?> mAdapter;
    private OnTouchListener mOnTouchListener;
    private hantizlabs.jeuduroi.CardAnimator mCardAnimator;
    private Context gameActivityContext;
    private GameActivity gameActivityView;
    private String descriptionCarte;
    private InitialiseJoueurs listeJoueurs;

    private CardEventListener mEventListener = new DefaultStackEventListener(300);
    private int mContentResource = 0;


    public interface CardEventListener{
        //section
        // 0 | 1
        //--------
        // 2 | 3
        // swipe distance, most likely be used with height and width of a view ;

        boolean swipeEnd(int section,float distance);
        boolean swipeStart(int section,float distance);
        boolean swipeContinue(int section, float distanceX,float distanceY );
        void discarded(int mIndex, int direction);
        void topCardTapped();
    }

    public void discardTop(final int direction){
        mCardAnimator.discard(direction, new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator arg0) {
                mCardAnimator.initLayout();
                mIndex++;
                loadLast();

                viewCollection.get(0).setOnTouchListener(null);
                viewCollection.get(viewCollection.size() - 1).setOnTouchListener(mOnTouchListener);
                mEventListener.discarded(mIndex - 1, direction);
            }
        });
    }

    public int getCurrIndex(){
        //sync?
        return mIndex;
    }

    //only necessary when I need the attrs from xml, this will be used when inflating layout
    public CardStack(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CardStack);
            mColor = array.getColor(R.styleable.CardStack_backgroundColor, mColor);
            array.recycle();
        }

        //get attrs assign minVisiableNum
        for(int i = 0; i<mNumVisible; i++){
            addContainerViews();
        }
        setupAnimation();
    }

    private void addContainerViews(){
        FrameLayout v =  new FrameLayout(getContext());
        viewCollection.add(v);
        addView(v);
    }

    public void setStackMargin(int margin){
        mCardAnimator.setStackMargin(margin);
        mCardAnimator.initLayout();
    }

    public void setContentResource(int res){
        mContentResource = res;
    }

    public void setCanSwipe(boolean can) {
        this.canSwipe = can;
    }

    public void reset(boolean resetIndex){
        if(resetIndex) mIndex = 0;
        removeAllViews();
        viewCollection.clear();
        for(int i = 0; i<mNumVisible; i++){
            addContainerViews();
        }
        setupAnimation();
        loadData();
        updateGameActivityView();
    }

    public void setVisibleCardNum(int visiableNum){
        mNumVisible = visiableNum;
        reset(false);
    }

    public void setThreshold(int t){
        mEventListener = new DefaultStackEventListener(t);
    }

    public void setListener(CardEventListener cel){
        mEventListener = cel;
    }
    private void setupAnimation(){
        final View cardView = viewCollection.get(viewCollection.size()-1);
        mCardAnimator = new CardAnimator(viewCollection, mColor);
        mCardAnimator.initLayout();

        final DragGestureDetector dd = new DragGestureDetector(CardStack.this.getContext(),new DragGestureDetector.DragListener(){

            @Override
            public  boolean onDragStart(MotionEvent e1, MotionEvent e2,
                                        float distanceX, float distanceY) {
                if (canSwipe) {
                    mCardAnimator.drag(e1, e2, distanceX, distanceY);
                }
                float x1 = e1.getRawX();
                float y1 = e1.getRawY();
                float x2 = e2.getRawX();
                float y2 = e2.getRawY();
                final int direction = CardUtils.direction(x1, y1, x2, y2);
                float distance = CardUtils.distance(x1, y1, x2, y2);
                mEventListener.swipeStart(direction, distance);
                return true;
            }

            @Override
            public boolean onDragContinue(MotionEvent e1, MotionEvent e2,
                                          float distanceX, float distanceY) {
                float x1 = e1.getRawX();
                float y1 = e1.getRawY();
                float x2 = e2.getRawX();
                float y2 = e2.getRawY();
                final int direction = CardUtils.direction(x1,y1,x2,y2);
                if (canSwipe) {
                    mCardAnimator.drag(e1, e2, distanceX, distanceY);
                }
                mEventListener.swipeContinue(direction, Math.abs(x2-x1), Math.abs(y2-y1));
                return true;
            }

            @Override
            public  boolean onDragEnd(MotionEvent e1, MotionEvent e2) {
                //reverse(e1,e2);
                float x1 = e1.getRawX();
                float y1 = e1.getRawY();
                float x2 = e2.getRawX();
                float y2 = e2.getRawY();
                float distance = CardUtils.distance(x1,y1,x2,y2);
                final int direction = CardUtils.direction(x1,y1,x2,y2);

                boolean discard = mEventListener.swipeEnd(direction, distance);
                if(discard){
                    if (canSwipe) {
                        mCardAnimator.discard(direction, new AnimatorListenerAdapter() {

                            @Override
                            public void onAnimationEnd(Animator arg0) {
                                mCardAnimator.initLayout();
                                mIndex++;
                                mEventListener.discarded(mIndex, direction);
                                //mIndex = mIndex%mAdapter.getCount();
                                loadLast();

                                viewCollection.get(0).setOnTouchListener(null);
                                viewCollection.get(viewCollection.size() - 1)
                                        .setOnTouchListener(mOnTouchListener);

                                //Update du nombre de cartes
                                //GameActivity gm = new GameActivity();
                                //gm.updateNombreCartes();
                                //gm.updateRegleCarte();

                                Log.i("nb de cartes", String.valueOf(mAdapter.getCount()));
                                updateGameActivityView();
                            }

                        });
                    }
                }else{
                    if (canSwipe) {
                        mCardAnimator.reverse(e1, e2);
                    }
                }
                return true;
            }

            @Override
            public boolean onTapUp() {
                mEventListener.topCardTapped();
                return true;
            }
        }
        );

        mOnTouchListener = new OnTouchListener() {
            private static final String DEBUG_TAG = "MotionEvents";
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                dd.onTouchEvent(event);
                return true;
            }
        };
        cardView.setOnTouchListener(mOnTouchListener);
    }

    private DataSetObserver mOb = new DataSetObserver(){
        @Override
        public void onChanged(){
            reset(false);
        }
    };


    //ArrayList

    ArrayList<View> viewCollection = new ArrayList<View>();
    public CardStack(Context context) {
        super(context);
    }

    public void setAdapter(final ArrayAdapter<?> adapter){
        if(mAdapter != null){
            mAdapter.unregisterDataSetObserver(mOb);
        }
        mAdapter = adapter;
        adapter.registerDataSetObserver(mOb);

        loadData();
    }

    public ArrayAdapter getAdapter() {
        return mAdapter;
    }

    public View getTopView() {
        return ((ViewGroup) viewCollection.get(viewCollection.size() - 1)).getChildAt(0);
    }

    private void loadData(){
        for(int i=mNumVisible-1 ; i>=0 ; i--) {
            ViewGroup parent = (ViewGroup) viewCollection.get(i);
            int index = (mIndex + mNumVisible - 1) - i;
            if (index > mAdapter.getCount() - 1) {
                parent.setVisibility(View.GONE);
            }else{
                View child = mAdapter.getView(index, getContentView(), this);
                parent.addView(child);
                parent.setVisibility(View.VISIBLE);
            }
        }
    }

    private View getContentView(){
        View contentView = null;
        if(mContentResource != 0) {
            LayoutInflater lf = LayoutInflater.from(getContext());
            contentView = lf.inflate(mContentResource,null);
        }
        return contentView;

    }

    private void loadLast(){
        ViewGroup parent = (ViewGroup)viewCollection.get(0);

        int lastIndex = (mNumVisible - 1)+ mIndex;
        if(lastIndex > mAdapter.getCount() -1 ){
            parent.setVisibility(View.GONE);
            return;
        }

        View child = mAdapter.getView(lastIndex, getContentView(), parent);
        Carte currentCarte = (Carte) mAdapter.getItem(lastIndex);
        updateDescriptionCarte(currentCarte.getDescription());
        parent.removeAllViews();
        parent.addView(child);
    }

    public int getStackSize() {
        return mNumVisible;
    }

    public int getCurrentIndex(){
        return mIndex;
    }

    public void updateGameActivityView(){

        gameActivityView.updateNombreCartes(mAdapter.getCount() - getCurrentIndex());
        gameActivityView.updateRegleCarte(getDescriptionCarte());
        gameActivityView.updateNextJoueur(listeJoueurs.getNextJoueur());
        Log.i("updateview", "done");
    }

    public void updateDescriptionCarte(String desc){
        this.descriptionCarte = desc;
    }

    public String getDescriptionCarte(){
        return descriptionCarte;
    }


    public void setGameActivityContext(Context ctx){
        this.gameActivityContext = ctx;
        this.gameActivityView = (GameActivity)gameActivityContext;
        listeJoueurs = (InitialiseJoueurs) gameActivityView.getApplicationContext();
    }
}
