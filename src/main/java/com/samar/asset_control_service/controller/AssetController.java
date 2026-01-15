package com.samar.asset_control_service.controller;

import com.samar.asset_control_service.entities.Asset;
import com.samar.asset_control_service.service.AssetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/assets")
public class AssetController {
    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        List<Asset> assets = this.assetService.findAll();
        return new ResponseEntity<>(assets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getById(@PathVariable("id") UUID id) {
        try {
            Asset asset = this.assetService.findAssetById(id);
            return new ResponseEntity<>(asset, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset) {
        Asset newAsset = this.assetService.addAsset(asset);
        return new ResponseEntity<>(newAsset, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable("id") UUID id, @RequestBody Asset assetDetails) {
        try {
            Asset updatedAsset = this.assetService.updateAsset(id, assetDetails);
            return new ResponseEntity<>(updatedAsset, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable("id") UUID id) {
        try {
            this.assetService.deleteAsset(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
