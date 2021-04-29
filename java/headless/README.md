# Headless

```
if (Desktop.isDesktopSupported()) 
{
  Desktop.getDesktop().browse("your url here");
}
```


```
if (GraphicsEnvironment.isHeadless()) {
     // non gui mode
} else {
     // gui mode
}
```