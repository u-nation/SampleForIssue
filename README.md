# SampleForIssue

I prepared this repository to reproduce the bug that only occurs in Lollipop(API 21~22).

|Lollipop|KitKat|
|:--:|:--:|
|![lollipop](https://github.com/u-nation/SampleForIssue/blob/master/art/lollipop.gif)|![kitkat](https://github.com/u-nation/SampleForIssue/blob/master/art/kitkat.gif)|

### Steps to reproduce

1. OS version: only Lollipop (I tried with versions Jelly Bean,KitKat and above Lollipop)
2. Prepare such layout as shown below in RecyclerView.ViewHolder.
    - Any ViewGroup is ok.
    - If TextView is present, it will be reproduced, otherwise it will not be reproduced

```xml
<Expandable ViewGroup>
    <ViewGroup>
        <TextView/>
        <ViewGroup as a container/>
    </ViewGroup>
</Expandable ViewGroup>
```
3. When the visibility of the expandable ViewGroup is GONE, the ViewGroup as a container calls removeAllViews() and addView(/* every view is ok*/) 

### Expected output
The View added to the ViewGroup as a container has a calculated height and is visible.

<img src=https://github.com/u-nation/SampleForIssue/blob/master/art/kitkat.jpg width=400px height=700px>



### Current output
The height of the View added to the ViewGroup as a container becomes 0, so it becomes invisible.

<img src=https://github.com/u-nation/SampleForIssue/blob/master/art/lollipop-bug.jpg width=400px height=700px>
