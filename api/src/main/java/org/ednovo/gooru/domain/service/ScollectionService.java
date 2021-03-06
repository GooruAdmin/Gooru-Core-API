/////////////////////////////////////////////////////////////
// ScollectionService.java
// gooru-api
// Created by Gooru on 2014
// Copyright (c) 2014 Gooru. All rights reserved.
// http://www.goorulearning.org/
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
/////////////////////////////////////////////////////////////
/**
 * 
 */
package org.ednovo.gooru.domain.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ednovo.gooru.core.api.model.ActionResponseDTO;
import org.ednovo.gooru.core.api.model.Code;
import org.ednovo.gooru.core.api.model.Collection;
import org.ednovo.gooru.core.api.model.CollectionItem;
import org.ednovo.gooru.core.api.model.Resource;
import org.ednovo.gooru.core.api.model.StandardFo;
import org.ednovo.gooru.core.api.model.User;
import org.springframework.util.MultiValueMap;

/**
 * @author Search Team
 * 
 */
public interface ScollectionService extends BaseService {

	ActionResponseDTO<Collection> createCollection(Collection collection, boolean addToShelf, String resourceId, String taxonomyCode, boolean updateTaxonomyByCode, String parentId) throws Exception;

	ActionResponseDTO<Collection> createCollection(Collection newCollection, boolean addToShelf, String resourceId, String parentId, User user) throws Exception;

	ActionResponseDTO<Collection> updateCollection(Collection newCollection, String updateCollectionId, String ownerUId, String creatorUId, boolean hasUnrestrictedContentAccess, String relatedContentId, User user) throws Exception;

	CollectionItem getCollectionItem(String collectionItemId, String includeAdditionalInfo, User user);

	Collection copyCollection(String collectionId, Collection newCollection, boolean addToShelf, String parentId, User user) throws Exception;

	ActionResponseDTO<CollectionItem> createResourceWithCollectionItem(String collectionId, Resource newResource, User user) throws Exception;

	List<Collection> getMyCollection(String limit, String offset, String orderBy, String fetchType, String resourceType, boolean skipPagination, User user);

	List<CollectionItem> getCollectionItems(String collectionId, Integer offset, Integer limit, boolean skipPagination, String orderBy, String type);

	ActionResponseDTO<Collection> updateCollection(Collection newCollection, String updateCollectionId, String taxonomyCode, String ownerUId, String creatorUId, boolean hasUnrestrictedContentAccess, String relatedContentId, boolean updateTaxonomyByCode, User apiCallerUser) throws Exception;

	void deleteCollection(String collectionId);

	List<Collection> getCollections(Map<String, String> filters, User user);

	ActionResponseDTO<CollectionItem> createCollectionItem(String resourceGooruOid, String collectionGooruOid, CollectionItem collectionItem, User user, String type, boolean isCreateQuestion) throws Exception;

	ActionResponseDTO<CollectionItem> updateCollectionItem(CollectionItem newcollectionItem, String collectionItemId, User user) throws Exception;

	List<CollectionItem> getCollectionItems(String collectionId, Map<String, String> filters);

	CollectionItem getCollectionItem(String collectionItemId, boolean includeAdditionalInfo, User user);

	void deleteCollectionItem(String collectionItemId, User user);

	ActionResponseDTO<CollectionItem> reorderCollectionItem(String collectionItemId, int newSequence) throws Exception;

	Collection getCollection(String collectionId, boolean includeMetaInfo, boolean includeCollaborator, boolean isContentFlag, User user, String merge);
		
	Collection copyCollection(String collectionId, String title, boolean addToShelf, User user, String taxonomyCode, String grade, String parentId) throws Exception;

	Collection getCollectionByGooruOid(String gooruOid, String gooruUid);

	CollectionItem getCollectionItemById(String collectionItemId);

	List<Collection> getResourceMoreInfo(String resourceGooruOid);

	User addCollaborator(String colletionId, User user, String collaboratorId, String collaboratorOperation);

	List<User> getCollaborators(String collectionId);

	List<Collection> getMyCollection(Map<String, String> filters, User user);

	List<Collection> getMyCollection(String offset, String limit, String type, String filter, User user);

	Collection updateCollectionMetadata(String collectionId, String creatorUId, String ownerUId, boolean hasUnrestrictedContentAccess, MultiValueMap<String, String> data, User user);

	CollectionItem updateCollectionItemMetadata(String collectionItemId, MultiValueMap<String, String> data, User apiCaller);

	CollectionItem copyCollectionItem(String collectionItemId, String collectionId) throws Exception;

	ActionResponseDTO<CollectionItem> createResourceWithCollectionItem(String collectionId, String title, String description, String url, String start, String stop, String thumbnailImgSrc, String resourceType, String category, User user) throws Exception;

	CollectionItem buildCollectionItemFromInputParameters(String data, User user);

	List<CollectionItem> getCollectionItemByResourceId(Long resourceId);

	List<CollectionItem> getMyCollectionItems(String partyUid, Map<String, String> filters, User user);

	List<CollectionItem> setCollectionItemMetaInfo(List<CollectionItem> collectionItems);

	Set<String> getCourse(Set<Code> taxonomySet);

	List<StandardFo> getStandards(Set<Code> taxonomySet, boolean ignoreUserTaxonomyPreference);
	
	void updateResourceSharing(String sharing, Collection collection);
	
	Map<String, Object>  getCollection(String gooruOid, Map<String, Object> collection);
	
	void updateFolderSharing(String collection);
	
	List<String> getParentCollection(String collectionGooruOid, String gooruUid, boolean reverse);
}
