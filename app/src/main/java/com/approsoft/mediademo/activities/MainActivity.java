package com.approsoft.mediademo.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.approsoft.mediademo.R;
import com.approsoft.mediademo.fragments.FoldersFragment;
import com.approsoft.mediademo.fragments.VideosFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//    int[] androidColors;
//    int randomAndroidColor;
//    List<SingleItem> itemsList = new ArrayList<>();
//    RecyclerView recyclerView;
//    private List<Video> videos = new ArrayList();
//    private List<Folder> folders = new ArrayList<>();
//    FoldersAdapter adapter;
//    private String[] VIDEO_COLUMNS = new String[]{"_id", "_display_name", "title", "date_added", "duration", "resolution", "_size", "_data", "mime_type"};
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frameMain,new FoldersFragment()).commit();

//        androidColors = getResources().getIntArray(R.array.flatColors);
//
//        File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
//
//        fetchAllVideos();
//        folders = fetchAllFolders();
//
//        adapter = new FoldersAdapter(folders, this);
////        setGridAdapter("/storage/sdcard/");
////        getFoldersList(root);
////        getAllMedia();
//        adapter.notifyDataSetChanged();
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        recyclerView.setAdapter(adapter);
//        recyclerView.smoothScrollToPosition(0);
    }

//    void getFoldersList(File f){
//        File[] files=f.listFiles();
//        itemsList.clear();
//        for (File file: files){
//            randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
//            SingleItem item = new SingleItem();
//            item.setItemTitle(file.getName());
//            item.setDirectory(true);
//            item.setItemCount("4326");
//            item.setItemBGColor(randomAndroidColor);
//            itemsList.add(item);
//        }
//        adapter.notifyDataSetChanged();
//    }
//
//    public List<Folder> fetchAllFolders() {
//        List<Folder> folders = new ArrayList();
//        for (Video video : this.videos) {
//            String parentFolder = new File(video.getData()).getParent();
//            String videoFolderName = new File(parentFolder).getName();
//            Folder aFolder = new Folder();
//            aFolder.setName(videoFolderName);
//            aFolder.setPath(parentFolder);
//            aFolder.videosPP();
//            aFolder.sizePP(video.getSize());
//            if (folders.contains(aFolder)) {
//                ((Folder) folders.get(folders.indexOf(aFolder))).videosPP();
//                ((Folder) folders.get(folders.indexOf(aFolder))).sizePP(video.getSize());
//            } else {
//                folders.add(aFolder);
//            }
//        }
//        return folders;
//    }
//
//    public List<Video> fetchAllVideos() {
//        Cursor videoCursor = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, this.VIDEO_COLUMNS, null, null, "date_added DESC");
//        if (videoCursor != null) {
//            this.videos = getVideosFromCursor(videoCursor);
//            videoCursor.close();
//        }
//        return this.videos;
//    }
//
//    private List<Video> getVideosFromCursor(Cursor videoCursor) {
//        List<Video> videos = new ArrayList();
//        while (videoCursor.moveToNext()) {
//            Video video = new Video();
//            video.set_ID(Long.parseLong(videoCursor.getString(videoCursor.getColumnIndexOrThrow("_id"))));
//            video.setName(videoCursor.getString(videoCursor.getColumnIndexOrThrow("_display_name")));
//            video.setTitle(videoCursor.getString(videoCursor.getColumnIndexOrThrow("title")));
//            video.setDateAdded(TheUtility.humanReadableDate(Long.parseLong(videoCursor.getString(videoCursor.getColumnIndexOrThrow("date_added"))) * 1000));
//            video.setDuration(TheUtility.parseTimeFromMilliseconds(videoCursor.getString(videoCursor.getColumnIndexOrThrow("duration"))));
//            video.setResolution(videoCursor.getString(videoCursor.getColumnIndexOrThrow("resolution")));
//            video.setSize(Long.parseLong(videoCursor.getString(videoCursor.getColumnIndexOrThrow("_size"))));
//            video.setSizeReadable(TheUtility.humanReadableByteCount(Long.parseLong(videoCursor.getString(videoCursor.getColumnIndexOrThrow("_size"))), false));
//            video.setData(videoCursor.getString(videoCursor.getColumnIndexOrThrow("_data")));
//            video.setMime(videoCursor.getString(videoCursor.getColumnIndexOrThrow("mime_type")));
//            videos.add(video);
//        }
//        return videos;
//    }
//
//    public void getAllMedia() {
//        HashSet<String> videoItemHashSet = new HashSet<>();
//        String[] projection = { MediaStore.Video.VideoColumns.DATA ,MediaStore.Video.Media.DISPLAY_NAME};
//        Cursor cursor = MainActivity.this.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
//        try {
//            cursor.moveToFirst();
//            do{
//                videoItemHashSet.add((cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA))));
//            }while(cursor.moveToNext());
//
//            cursor.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//        //use one of overloaded setDataSource() functions to set your data source
//        for(String filePath: videoItemHashSet){
//            retriever.setDataSource(filePath);
//            String title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
//            String duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
////            String displayIcon = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
//            String width = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
//            String height = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);
//            String resolution = width + " X " + height;
//
//            SingleItem item = new SingleItem();
//            item.setItemTitle(title);
//            item.setItemDuration(duration);
//            item.setItemDimension(resolution);
//            itemsList.add(item);
//        }
//        adapter.notifyDataSetChanged();
//
//        ArrayList<String> downloadedList = new ArrayList<>(videoItemHashSet);
//        return downloadedList;
//    }

//    private void setGridAdapter(String path) {
//        // Create a new grid adapter
//        itemsList = createGridItems(path);
//        adapter.notifyDataSetChanged();
//    }
//
//    private List<SingleItem> createGridItems(String directoryPath) {
//        List<SingleItem> items = new ArrayList<SingleItem>();
//
//        // List all the items within the folder.
//        File[] files = new File(directoryPath).listFiles(new VideoFileFilter());
//        for (File file : files) {
//
//            SingleItem item = new SingleItem();
//            // Add the directories containing images or sub-directories
//            if (file.isDirectory()
//                    && file.listFiles(new VideoFileFilter()).length > 0) {
//                item.setItemTitle(file.getName());
//                item.setItemCount("4326");
//                item.setDirectory(true);
//                items.add(item);
//            }
//            // Add the images
//            else {
//                item.setItemTitle(file.getName());
//                item.setItemCount("4326");
//                item.setDirectory(false);
//                items.add(item);
//            }
//        }
//
//        return items;
//    }
//
//    /**
//     * Checks the file to see if it has a compatible extension.
//     */
//    private boolean isVideoFile(String filePath) {
//        if (filePath.endsWith(".mp4") || filePath.endsWith(".wmv") || filePath.endsWith(".flv"))
//        // Add other formats as desired
//        {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * This can be used to filter files.
//     */
//    private class VideoFileFilter implements FileFilter {
//
//        @Override
//        public boolean accept(File file) {
//            if (file.isDirectory()) {
//                return true;
//            }
//            else if (isVideoFile(file.getAbsolutePath())) {
//                return true;
//            }
//            return false;
//        }
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.folders, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all_videos) {
            fragmentManager.beginTransaction().add(R.id.frameMain,new VideosFragment()).commit();
        } else if (id == R.id.nav_folders) {
            fragmentManager.beginTransaction().add(R.id.frameMain,new FoldersFragment()).commit();
        } else if (id == R.id.nav_about) {
            fragmentManager.beginTransaction().add(R.id.frameMain,new FoldersFragment()).commit();
        } else if (id == R.id.nav_rate) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
