/**
 * Copyright (c) 2015-present, Facebook, Inc.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 *
 * Stub of rnbgupload for Android.
 *
 * @format
 * @flow
 */

'use strict';

const Nativernbgupload = require('NativeModules').RNUpload;

const rnbgupload = {
  test: function() {
  		Nativernbgupload.test();
  }
};

module.exports = rnbgupload;
