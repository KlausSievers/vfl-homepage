app.config(function ($mdIconProvider) {
  var spritePath = '/img/icons/sprites/svg-sprite/svg-sprite-';
  $mdIconProvider.iconSet('action', spritePath + 'action.svg')
          .iconSet('alert', spritePath + 'alert.svg')
          .iconSet('av', spritePath + 'av.svg')
          .iconSet('communication', spritePath + 'communication.svg')
          .iconSet('content', spritePath + 'content.svg')
          .iconSet('device', spritePath + 'device.svg')
          .iconSet('editor', spritePath + 'editor.svg')
          .iconSet('file', spritePath + 'file.svg')
          .iconSet('hardware', spritePath + 'hardware.svg')
          .iconSet('image', spritePath + 'image.svg')
          .iconSet('maps', spritePath + 'maps.svg')
          .iconSet('navigation', spritePath + 'navigation.svg')
          .iconSet('notification', spritePath + 'notification.svg')
          .iconSet('social', spritePath + 'social.svg')
          .iconSet('toogle', spritePath + 'toogle.svg');
});


