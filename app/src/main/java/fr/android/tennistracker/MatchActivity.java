package fr.android.tennistracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MatchActivity extends AppCompatActivity implements FragmentScore.FragmentScoreListener {

    public enum Evenement{
        ACE,
        POINT_GAGNANT,
        FAUTE_DIRECTE,
        FAUTE_PROVOQUEE,
        DOUBLE_FAUTE
    }

    private FragmentScore fragmentScore;
    private FragmentService fragmentService;
    private FragmentEchange fragmentEchange;
    private FragmentUtils fragmentUtils;

    private boolean j1_sert;
    private int set_en_cours;
    private static final int REQUEST_TAKE_PHOTO = 1;

    private String currentPhotoPath;
    private Bitmap image;
    private File photoFile = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.menu_demarrer);
        item.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);


        final Intent intentBackNewMatch = new Intent(this, NewMatch.class);

        String nomJoueur1 = null;
        String nomJoueur2 = null;
        int nb_jeux_max = 6;
        int tie_break = 6;
        boolean avantage = true;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nomJoueur1 = extras.getString("NOM_JOUEUR_1");
            nomJoueur2 = extras.getString("NOM_JOUEUR_2");
            nb_jeux_max = extras.getInt("NB_JEUX");
            avantage = extras.getBoolean("AVANTAGE");
            tie_break = extras.getInt("TIE_BREAK");
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.enregistrement);
        invalidateOptionsMenu();

        final String[] listItems = new String[]{
                (nomJoueur1.isEmpty()) ? getString(R.string.joueur_1_lcase) : nomJoueur1,
                (nomJoueur2.isEmpty()) ? getString(R.string.joueur_2_lcase) : nomJoueur2
        };

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MatchActivity.this);
        mBuilder.setTitle(R.string.premier_serveur);
        mBuilder.setItems(listItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0)
                    setServer(true);
                else
                    setServer(false);
            }
        });
        mBuilder.setNegativeButton(getString(R.string.annuler), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(intentBackNewMatch);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);

        fragmentScore = new FragmentScore();
        fragmentService = new FragmentService();
        fragmentEchange = new FragmentEchange();
        fragmentUtils = new FragmentUtils();


        Bundle bundleFragScore = new Bundle();
        Bundle bundleFragService = new Bundle();

        if(j1_sert)
            bundleFragService.putString("serveur",nomJoueur1);
        else
            bundleFragService.putString("serveur", nomJoueur2);

        bundleFragScore.putString("NOM_JOUEUR_1", nomJoueur1);
        bundleFragScore.putString("NOM_JOUEUR_2", nomJoueur2);
        bundleFragScore.putBoolean("AVANTAGE", avantage);
        bundleFragScore.putInt("NB_JEUX", nb_jeux_max);
        bundleFragScore.putInt("TIE_BREAK", tie_break);

        fragmentScore.setArguments(bundleFragScore);
        fragmentService.setArguments(bundleFragService);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.score_layout, fragmentScore)
                .add(R.id.service_layout, fragmentService)
                .add(R.id.echange_layout, fragmentEchange)
                .add(R.id.utils_layout, fragmentUtils)
                .commit();
    }

    @Override
    public void setServer(boolean joueur1_sert) {
        fragmentScore.init_set1();
        fragmentScore.setServer(joueur1_sert);
        j1_sert = joueur1_sert;
        set_en_cours = 1;
    }

    public void onInputService(View view) {
        Button myBtn = (Button) view;
        switch (myBtn.getTag().toString()) {
            case "ace":
                fragmentScore.updateScore(j1_sert, Evenement.ACE);
                break;
            case "double_faute":
                fragmentScore.updateScore(!j1_sert, Evenement.DOUBLE_FAUTE);
                break;
            case "point_gagnant_j1":
                fragmentScore.updateScore(true, Evenement.POINT_GAGNANT);
                break;
            case "faute_provoq_j2":
                fragmentScore.updateScore(true, Evenement.FAUTE_PROVOQUEE);
                break;
            case "faute_directe_j2":
                fragmentScore.updateScore(true, Evenement.FAUTE_DIRECTE);
                break;
            case "point_gagnant_j2":
                fragmentScore.updateScore(false, Evenement.POINT_GAGNANT);
                break;
            case "faute_provoq_j1":
                fragmentScore.updateScore(false, Evenement.FAUTE_PROVOQUEE);
                break;
            case "faute_directe_j1":
                fragmentScore.updateScore(false, Evenement.FAUTE_DIRECTE);
                break;

            case "btnScoreDetails":
                Intent i = new Intent(this, ScoreDetailsActivity.class);
                Bundle infosMatch = new Bundle();
                infosMatch.putSerializable("infos_match", fragmentScore.getInfosMatch());
                i.putExtras(infosMatch);
                startActivity(i);
                break;

            default:
                break;
        }
        if (fragmentScore.finJeu()) {
            fragmentScore.resetJeu();
            inverseService();
        }

    }

    public void inverseService() {
        j1_sert = !j1_sert;
        fragmentScore.setServer(j1_sert);
    }

    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(MatchActivity.this,
                        this.getApplicationContext().getPackageName() + ".provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        galleryAddPic();
        return image;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_terminer:
                final String[] listItems = new String[]{
                        getString(R.string.annuler_l_enregistrement),
                        getString(R.string.terminer_sans_vainqueur),
                        getString(R.string.declarer_j1_vainqueur),
                        getString(R.string.declarer_j2_vainqueur)
                };
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MatchActivity.this);
                mBuilder.setTitle(R.string.match_non_termine);
                mBuilder.setItems(listItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO
                        dialog.dismiss();
                    }
                });
                mBuilder.setNegativeButton(getString(R.string.annuler), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.setCancelable(false);
                mDialog.show();

                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        image = BitmapFactory.decodeFile(currentPhotoPath);
        MediaStore.Images.Media.insertImage(getContentResolver(), image, photoFile.getName(), "cool");
    }
}
