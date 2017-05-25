package com.example.wikipeng.coordinatorlayoutdemo00;

import android.app.Application;

import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;

/**
 * Created by wikipeng on 2017/5/25.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            //            LeakCanary.install(this);
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableDumpapp(new DumperPluginsProvider() {
                        @Override
                        public Iterable<DumperPlugin> get() {
                            return new Stetho.DefaultDumperPluginsBuilder(MainApplication.this)
                                    //                                    .provide(new MyDumperPlugin())
                                    .finish();
                        }
                    })
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                    .build());
        }
    }
}
