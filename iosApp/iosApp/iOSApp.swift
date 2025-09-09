import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        MyAppModuleKt.doInitKoin()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
