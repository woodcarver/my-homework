<?php
$factories=array(
    'position'=>10,
    'scale'=>10,
    'industry'=>7,
    'salary'=>9,
    'team'=>10,
    'place'=>6,
    'dailyTime'=>6,
);

$companies=array(
    'Alibaba'=>array(
        'position'=>10,
        'scale'=>10,
        'industry'=>10,
        'salary'=>0,
        'team'=>10,
        'place'=>10,
        'dailyTime'=>5,
    ),
    'Baidu'=>array(
        'position'=>10,
        'scale'=>10,
        'industry'=>10,
        'salary'=>0,
        'team'=>10,
        'place'=>4,
        'dailyTime'=>5,
    ),
    'Tencent'=>array(
        'position'=>10,
        'scale'=>10,
        'industry'=>10,
        'salary'=>0,
        'team'=>10,
        'place'=>4,
        'dailyTime'=>7,
    ),
    'Zhihu'=>array(
        'position'=>10,
        'scale'=>4,
        'industry'=>10,
        'salary'=>0,
        'team'=>10,
        'place'=>4,
        'dailyTime'=>10,
    ),
);

function print_array($arr)
{
    if(!is_array($arr))
        echo "$arr\n";

    foreach($arr as $kitem=>$item){
        echo "$kitem\n";
        print_array($item);
    }
}
$rake=array();
foreach($companies as $kcomp=>$comp){
    $grade=0;
    foreach($factories as $kfact=>$fact){
       $grade+=$fact*$comp[$kfact];
    }
    $rake[$kcomp]=$grade;
    echo "$kcomp:$grade\n";
}
//sort($rake);
print_r($rake);
print_array($rake);
