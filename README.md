# Server-Driven UI

Android application that shows how to draw your screen UI from a remote response and how to enable navigation through application screens.

### Client-Driven UI vs Server-Driven UI
- Client-Driven UI
  <br/>Our traditional way to get the data from backend and transform it to UI by the client (android - iOS - Web).
- Server-Driven UI
  <br/>Is an emerging technique used by companies like Airbnb and Lyft that leverage the server to build the user interfaces of their mobile apps. Both UI and data come from the backend.<br/>
  The backend could control UI, the data displayed on the UI (title, subtitle and more) for each client (android - iOS - web), the actions that user can trigger, how to handle actions triggered by the user and more.

### Key Terms

- [Design Language System (DLS)](https://karrisaarinen.com/dls/)
  <br/>Set of reusable elements like a visual language, UI kit, front components, and various documentation. It helps designers to handle design at scale. It is like of series of elements that can be reused by an entire team.

### Prerequisites
- DataBinding
- ViewModel
- RxJava

### How UI is drwan on the screen structure
![project data flow structure](https://github.com/sh3lan93/ServerDrivenUIExample/blob/master/art/Server-Driven%20UI%20project%20structure.drawio.png)

### Libraies Used
- [Retrofit](https://github.com/square/retrofit)
- [RxJava](https://github.com/ReactiveX/RxJava)
- [Coil](https://github.com/coil-kt/coil)
- [Epoxy](https://github.com/airbnb/epoxy)
- [Koin](https://github.com/InsertKoinIO/koin)

### References
- [Deep dive into aribnbs server driven ui ](https://medium.com/airbnb-engineering/a-deep-dive-into-airbnbs-server-driven-ui-system-842244c5f5)
- [Airbnb design system](https://mockitt.wondershare.com/design/airbnb-design-system.html#:~:text=Airbnb%20design%20system%20is%20a,reused%20by%20an%20entire%20team.)
---
<a href="https://www.buymeacoffee.com/sh3lan93" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/v2/default-yellow.png" alt="Buy Me A Coffee" style="height: 60px !important;width: 217px !important;" ></a>

